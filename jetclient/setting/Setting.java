    package dev.jetclient.setting;

    public abstract class Setting {
        private final String name;
        private boolean active;

        public Setting(String name) {
            this.name = name;
        }

        public boolean getActive() {
            return this.active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public String getName() {
            return this.name;
        }

        public abstract void handleClick(int mouseX, int mouseY);
        public abstract void draw(int x, int y);
    }
