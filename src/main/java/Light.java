/**
 * Class defining a fairylight.
 */
public class Light {

    private Integer id;
    private Colour colour;
    private boolean lit;

    /**
     * Constructor
     * @param id id of the light
     * @param colour the light colour
     */
    public Light(Integer id, Colour colour) {
        this.id = id;
        this.colour = colour;
        lit = false;
    }

    /**
     * Builds a string representation
     * for display purposes.
     * @return info string of the light.
     */
    public String display (){
        StringBuilder builder = new StringBuilder();
            builder.append("Light ");
            builder.append(this.id);
            builder.append(" ");
            builder.append(this.colour);
            builder.append(" ");
            builder.append(lit? "on": "off");

       return builder.toString();
    }

    public Integer getId() {
        return id;
    }

    public Colour getColour() {
        return colour;
    }

    /**
     * String representation of the boolean on/off state.
     * @return
     */
    public String isLit() {
        return (lit? "on": "off");
    }

    /**
     * Set the light to a specific on/off state.
     * @param lit on or off.
     */
    public void setLit(boolean lit) {
        this.lit = lit;
    }

    /**
     * Invert the lights current lit state.
     */
    public void toggle(){
        this.lit = !this.lit;
    }

}
