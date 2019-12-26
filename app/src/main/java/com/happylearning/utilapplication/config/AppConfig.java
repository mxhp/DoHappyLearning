package com.happylearning.utilapplication.config;

public class AppConfig {
    private boolean showMeizi;

    private AppConfig(Builder AppConfigBuilder) {
        this.showMeizi = AppConfigBuilder.showMeizi;
    }

    public static class Builder {
        private boolean showMeizi;

        public Builder showMeizi(boolean show) {
            this.showMeizi = show;
            return this;
        }

        public AppConfig build() {
            return new AppConfig(this);
        }
    }
}


