import type { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'com.agriface.app',
  appName: 'AgriFace',
  webDir: 'dist',
  server: {
    androidScheme: 'https'
  },
  plugins: {
    CapacitorUpdater: {
      autoUpdate: false
    },
    SplashScreen: {
      launchShowDuration: 500,
      launchAutoHide: true,
      backgroundColor: "#ffffff",
      showSpinner: false,
      splashFullScreen: true,
      splashImmersive: true
    }
  }
};

export default config;
