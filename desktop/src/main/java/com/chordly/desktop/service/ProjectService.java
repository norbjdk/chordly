package com.chordly.desktop.service;

import com.chordly.desktop.model.dto.internal.CreateProjectRequest;
import com.chordly.desktop.model.dto.internal.OpenProjectRequest;
import com.chordly.desktop.model.entity.ProjectEntity;
import com.chordly.desktop.model.music.Measure;
import com.chordly.desktop.model.music.Note;
import com.chordly.desktop.model.music.Part;
import com.chordly.desktop.util.Logger;
import nu.xom.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProjectService {
    private final Builder builder;
    private File musicXML;

    public ProjectService() {
        builder = new Builder();
    }

    public ProjectEntity openProject(OpenProjectRequest request) {
        ProjectEntity project = new ProjectEntity();
        try {
            setMusicXML(request.getFile());
            Document document = builder.build(musicXML);

            readMetadata(document, project);
            List<Part> parts = parseParts(document);
            project.setParts(parts);
            project.setLocalPath(musicXML.getAbsolutePath());

            Logger.debug("Loaded project: " + project.getProjectTitle() + " with " + parts.size() + " parts");
        } catch (Exception e) {
            Logger.err("Failed to open project: " + e.getMessage());
            e.printStackTrace();
        }
        return project;
    }

    public ProjectEntity createProject(CreateProjectRequest request) {
        ProjectEntity project = new ProjectEntity();
        project.setProjectTitle(request.getTitle());
        project.setProjectAlbum(request.getAlbum());
        project.setProjectAuthor(request.getAuthor());
        return project;
    }

    private void setMusicXML(File musicXML) {
        this.musicXML = musicXML;
    }

    private void readMetadata(Document document, ProjectEntity project) {
        Element root = document.getRootElement();

        Element work = root.getFirstChildElement("work");
        if (work != null) {
            Element title = work.getFirstChildElement("work-title");
            if (title != null) {
                project.setProjectTitle(title.getValue());
            }
        }

        Element identification = root.getFirstChildElement("identification");
        if (identification != null) {
            Element creator = identification.getFirstChildElement("creator");
            if (creator != null) {
                project.setProjectAuthor(creator.getValue());
            }
        }
    }

    private List<Part> parseParts(Document document) {
        List<Part> parts = new ArrayList<>();
        Element root = document.getRootElement();

        Element partList = root.getFirstChildElement("part-list");

        Elements partElements = root.getChildElements("part");
        for (int i = 0; i < partElements.size(); i++) {
            Element partElement = partElements.get(i);
            String partId = partElement.getAttributeValue("id");

            String partName = getPartName(partList, partId);
            Part part = new Part(partId, partName);

            Elements measureElements = partElement.getChildElements("measure");
            for (int j = 0; j < measureElements.size(); j++) {
                Element measureElement = measureElements.get(j);
                Measure measure = parseMeasure(measureElement);
                part.addMeasure(measure);
            }

            parts.add(part);
            Logger.debug("Parsed part: " + partName + " with " + part.getMeasures().size() + " measures");
        }

        return parts;
    }

    private String getPartName(Element partList, String partId) {
        if (partList == null) return partId;

        Elements scoreParts = partList.getChildElements("score-part");
        for (int i = 0; i < scoreParts.size(); i++) {
            Element scorePart = scoreParts.get(i);
            if (partId.equals(scorePart.getAttributeValue("id"))) {
                Element partName = scorePart.getFirstChildElement("part-name");
                if (partName != null) {
                    return partName.getValue();
                }
            }
        }
        return partId;
    }

    private Measure parseMeasure(Element measureElement) {
        int measureNumber = Integer.parseInt(measureElement.getAttributeValue("number"));
        Measure measure = new Measure(measureNumber);

        Element attributes = measureElement.getFirstChildElement("attributes");
        if (attributes != null) {
            parseAttributes(attributes, measure);
        }

        for (int i = 0; i < measureElement.getChildCount(); i++) {
            Node node = measureElement.getChild(i);
            if (node instanceof Element) {
                Element element = (Element) node;
                if ("note".equals(element.getLocalName())) {
                    Note note = parseNote(element);
                    measure.addNote(note);
                }
            }
        }

        return measure;
    }

    private void parseAttributes(Element attributes, Measure measure) {
        Element divisions = attributes.getFirstChildElement("divisions");
        if (divisions != null) {
            measure.setDivisions(Integer.parseInt(divisions.getValue()));
        }

        Element key = attributes.getFirstChildElement("key");
        if (key != null) {
            Element fifths = key.getFirstChildElement("fifths");
            if (fifths != null) {
                measure.setFifths(Integer.parseInt(fifths.getValue()));
            }
        }

        Element time = attributes.getFirstChildElement("time");
        if (time != null) {
            Element beats = time.getFirstChildElement("beats");
            Element beatType = time.getFirstChildElement("beat-type");
            if (beats != null && beatType != null) {
                measure.setBeats(Integer.parseInt(beats.getValue()));
                measure.setBeatType(Integer.parseInt(beatType.getValue()));
            }
        }

        Element clef = attributes.getFirstChildElement("clef");
        if (clef != null) {
            Element sign = clef.getFirstChildElement("sign");
            Element line = clef.getFirstChildElement("line");
            if (sign != null) {
                measure.setClef(sign.getValue());
            }
            if (line != null) {
                measure.setClefLine(Integer.parseInt(line.getValue()));
            }
        }
    }

    private Note parseNote(Element noteElement) {
        Note note = new Note();

        Element rest = noteElement.getFirstChildElement("rest");
        note.setRest(rest != null);

        if (!note.isRest()) {
            Element pitch = noteElement.getFirstChildElement("pitch");
            if (pitch != null) {
                Element step = pitch.getFirstChildElement("step");
                Element octave = pitch.getFirstChildElement("octave");
                Element alter = pitch.getFirstChildElement("alter");

                if (step != null) note.setStep(step.getValue());
                if (octave != null) note.setOctave(Integer.parseInt(octave.getValue()));
                if (alter != null) note.setAlter(Integer.parseInt(alter.getValue()));
            }
        }

        Element duration = noteElement.getFirstChildElement("duration");
        if (duration != null) {
            note.setDuration(Integer.parseInt(duration.getValue()));
        }

        Element type = noteElement.getFirstChildElement("type");
        if (type != null) {
            note.setType(type.getValue());
        }

        Element voice = noteElement.getFirstChildElement("voice");
        if (voice != null) {
            note.setVoice(Integer.parseInt(voice.getValue()));
        }

        Element staff = noteElement.getFirstChildElement("staff");
        if (staff != null) {
            note.setStaff(Integer.parseInt(staff.getValue()));
        }

        Element stem = noteElement.getFirstChildElement("stem");
        if (stem != null) {
            note.setHasStem(true);
            note.setStemDirection(stem.getValue());
        }

        return note;
    }
}