// TweakInterface.aidl
package tweakinterface;

// Declare any non-default types here with import statements

interface ITweakInterface {

        boolean existFile(String filepath);
        boolean canWriteFile(String filepath);
        boolean writeFile(String path, String text);
        String readFile(String file);
        String getVersion();
}