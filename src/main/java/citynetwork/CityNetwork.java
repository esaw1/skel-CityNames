package citynetwork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CityNetwork {
    public final List<CityTLA> _cities = new ArrayList<>();

    public boolean checkRep() {
        if (_cities.isEmpty()) {
            return false;
        }
        for (int i = 0; i < _cities.size(); i++) {
            CityTLA city1 = _cities.get(i);
            if (!city1.checkRep()) {
                return false;
            }
            for (CityTLA city2 : _cities) {
                if (!city2.equals(city1)
                 && Utils.isValidCode(city2._cityName, city1._cityCode)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     * @param cityNames is not null and only contains strings with upper-case alphabets and spaces
     * @return a {@code CityNetwork} instance with the maximum number of cities from {@code cityNames} that can have ambiguity-free TLAs
     */
    public static final CityNetwork buildNetwork(List<String> cityNames) {
        Map<String, Set<String>> codeMap = new HashMap<>();
        for (String cityName : cityNames) {
            codeMap.put(cityName, getCodeSet(cityName));
        }

        CityNetwork cityNet = new CityNetwork();
        for (int i = 0; i < cityNames.size(); i++) {
            String cityName1 = cityNames.get(i);
            boolean valid = true;
            for (String testCode : codeMap.get(cityName1)) {
                for (int j = 0; j < cityNames.size(); j++) {
                    String cityName2 = cityNames.get(j);
                    if (i != j && Utils.isValidCode(cityName2, testCode)) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    cityNet._cities.add(new CityTLA(cityName1, testCode));
                    System.out.println(cityName1);
                    System.out.println(testCode);
                    break;
                }
            }
            System.out.println();
        }
        return cityNet;
    }

    private static Set<String> getCodeSet(String cityName) {
        Set<String> possibleCodes = new HashSet<>();
        for (int start = 0; start < cityName.length() - 2; start++) {
            for (int end = start + 2; end < cityName.length(); end++) {
                for (int middle = start + 1; middle < end; middle++) {
                    String code = cityName.charAt(start) + "" +
                                  cityName.charAt(middle) +
                                  cityName.charAt(end);
                    possibleCodes.add(code);
                }
            }
        }
        if (possibleCodes.isEmpty()) {
            return Collections.emptySet();
        } else {
            return possibleCodes;
        }
    }
}
