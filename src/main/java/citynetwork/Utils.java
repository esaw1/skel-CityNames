package citynetwork;

public class Utils {

    public static boolean isValidCode(String _cityName, String _cityCode) {
        if (_cityCode.matches("[A-Z]{3}")) {
            int codeChar = 0;
            for (int i = 0; i < _cityName.length(); i++) {
                if (_cityCode.charAt(codeChar) == _cityName.charAt(i)) {
                    codeChar++;
                }
                if (codeChar == 3) {
                    return true;
                }
            }
        }
        return false;
    }

}
