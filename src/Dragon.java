public class Dragon extends Cow {
    public Dragon(String name, String image) {
        super(name);
        super.setImage(image);
    }

    public boolean canBreatheFire() {
        return true;
    }
}