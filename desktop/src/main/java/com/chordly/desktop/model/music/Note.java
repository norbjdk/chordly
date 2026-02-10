package com.chordly.desktop.model.music;

public class Note {
    private String step;
    private int octave;
    private int alter;
    private int duration;
    private String type;
    private boolean isRest;
    private int voice;
    private int staff;
    private boolean hasStem;
    private String stemDirection;

    public Note() {}

    public String getStep() { return step; }
    public void setStep(String step) { this.step = step; }

    public int getOctave() { return octave; }
    public void setOctave(int octave) { this.octave = octave; }

    public int getAlter() { return alter; }
    public void setAlter(int alter) { this.alter = alter; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public boolean isRest() { return isRest; }
    public void setRest(boolean rest) { isRest = rest; }

    public int getVoice() { return voice; }
    public void setVoice(int voice) { this.voice = voice; }

    public int getStaff() { return staff; }
    public void setStaff(int staff) { this.staff = staff; }

    public boolean hasStem() { return hasStem; }
    public void setHasStem(boolean hasStem) { this.hasStem = hasStem; }

    public String getStemDirection() { return stemDirection; }
    public void setStemDirection(String stemDirection) { this.stemDirection = stemDirection; }

    @Override
    public String toString() {
        if (isRest) {
            return "REST(" + type + ")";
        }
        String alterStr = alter == 1 ? "#" : (alter == -1 ? "b" : "");
        return step + alterStr + octave + " (" + type + ")";
    }
}