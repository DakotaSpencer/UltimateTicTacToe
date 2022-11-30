package main;

public class Player {
    private String Marker;
    private String Colour;
    public Player() { }

    public Player(String marker) {
        setMarker(marker);
    }

    public String getMarker() {
        return Marker;
    }

    public void setMarker(String marker) {
        Marker = marker;
    }

    public String getColour() {
        return Colour;
    }

    public void setColour(String colour) {
        Colour = colour;
    }
}
