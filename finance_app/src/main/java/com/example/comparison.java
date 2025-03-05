//In the GUI class, the MintermObserver now references gui so it can directly
//interact with the observer
public void updateMintermDisplay() {
        resultTextArea.setText("");
    }

[...]

MintermObserver observer = new MintermObserver(gui);

//In the MintermObserver class, the MintermObserver is updated directly
private final GUI gui;
    public MintermObserver(GUI gui) {
        this.gui = gui;
    }

[...]

gui.updateMintermDisplay();
