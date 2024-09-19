package Model;

public class itemModel {

    private String Text;
    private boolean IsUser;

    public itemModel(String text, boolean isUser) {
        Text = text;
        IsUser = isUser;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public boolean isUser() {
        return IsUser;
    }

    public void setUser(boolean user) {
        IsUser = user;
    }
}
