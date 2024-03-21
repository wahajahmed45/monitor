package monitor2.domains;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Grammar {
    private final String RX_LETTER="[a-zA-Z]";//"[a-zA-Z]";
    private final String RX_DIGIT="\\d";//"[0-9]";
    private final String RX_LETTER_DIGIT= RX_LETTER + "|" + RX_DIGIT;//"[a-zA-Z0-9]";
    private final String RX_CRLF= "(\\x0d\\x0a){0,1}";//"[\\x0d\\x0a] \r \c";
    private final String RX_PORT= "(("+RX_DIGIT +"){1,5})";//"[0-9]{1,5}";
    private final String RX_VISIBLE_CHARACTER ="[\\x20-\\xff]";//"[\\x20-\\xff]" All visible characters
    private final String RX_CHARACTER_SPEC = "[\\x21-\\x2f]|[\\x3a-\\x40]|[\\x5B-\\x60]"; // "-"/"_"/"."/"="/"+"/"*"/"$"/"°"/"("/")"/"["/"]"/"{"/"}"/"^"/"%" All special characters
    private final String RX_CHARACTER_PASS = RX_LETTER_DIGIT + "|" + RX_CHARACTER_SPEC;//"[a-zA-Z0-9]+All special characters";
    private final String RX_SP =" ";//a space
    private final String RX_ID = "((" + RX_LETTER_DIGIT +"){5,10})";//"[a-zA-Z0-9]{5,10}";
    private final String RX_PROTOCOL = "((" + RX_LETTER_DIGIT +"){3,15})";//"[a-zA-Z0-9]{3,15}";
    private final String RX_USERNAME = "((" + RX_LETTER_DIGIT +"){3,50})";//"[a-zA-Z0-9]{3,50}";
    private final String RX_PASSWORD = "((" + RX_CHARACTER_PASS +"){3,50})";//"[a-zA-Z0-9]+All special characters"{3,50}";
    private final String RX_AUTHENTICATION = "((" + RX_CHARACTER_PASS +"){3,50})";//"[a-zA-Z0-9]+All special characters"{3,50}";
    private final String RX_PASSWORD_AUTH = RX_PASSWORD + "#" + RX_AUTHENTICATION;//"[a-zA-Z0-9]+All special characters" + # +[a-zA-Z0-9]+All special characters";
    private final String RX_HOST = "(" + RX_LETTER_DIGIT + "|[._-]){3,50}";//"[a-zA-Z0-9]+All special characters"{3,50}";
    private final String RX_PATH =  "/((" +RX_LETTER_DIGIT+"|"+"([._/-])){0,100})";//" / + (([a-zA-Z0-9]) + [._/-] ){0,100}";
    private final String RX_URL = RX_PROTOCOL + "://(" + RX_USERNAME + "(:" + RX_PASSWORD_AUTH + ")?@)?" + RX_HOST + "(:" + RX_PORT + ")?" + "(" + RX_PATH + ")";
    private final String RX_MIN = "(("+RX_DIGIT +"){1,8})";//[0-9]{1,8}";
    private final String RX_MAX = "(("+RX_DIGIT +"){1,8})";//"[0-9]{1,8}";
    private final String RX_FREQUENCY = "(("+RX_DIGIT +"){1,8})";//"[0-9]{1,8}";
    private final String RX_AUGMENTED_URL = RX_ID + "!" + RX_URL + "!" + RX_MIN + "!" + RX_MAX;
    private final String RX_STATE = "OK" + "|" + "ALARM" + "|" + "DOWN" + "|" + "UNKNOWN";
    private final String RX_MESSAGE = "((" + RX_VISIBLE_CHARACTER + "){1,200})";

    //----------------------------------ValidationsMethods----------------------------------------------

    /**
     * Method to validate if a given string matches the RX_URL pattern
     * @param  url
     * @return boolean
     */
    public boolean validateURL(String url){
        Pattern pattern = Pattern.compile(RX_URL);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    /**
     * Method to validate if a given string matches the RX_MESSAGE pattern
     * @param  message
     * @return boolean
     */
    public boolean validateMessage(String message){
        Pattern pattern = Pattern.compile(RX_MESSAGE);
        Matcher matcher = pattern.matcher(message);
        return matcher.matches();
    }

    /**
     * Method to validate if a given string matches the RX_AUGMENTED_URL pattern
     * @param  augmentedURL
     * @return boolean
     */
    public boolean validateAugmentedURL(String augmentedURL){
        Pattern pattern = Pattern.compile(RX_AUGMENTED_URL);
        Matcher matcher = pattern.matcher(augmentedURL);
        return matcher.matches();
    }


    /**
     * Method to validate if a given string matches the RX_STATE pattern
     * @param  state
     * @return boolean
     */
    public boolean validateState(String state){
        Pattern pattern = Pattern.compile(RX_STATE);
        Matcher matcher = pattern.matcher(state);
        return matcher.matches();
    }

    /**
     * Method to validate if a given string matches the RX_FREQUENCY pattern
     * @param  frequency
     * @return boolean
     */
    public boolean validateFrequency(String frequency){
        Pattern pattern = Pattern.compile(RX_FREQUENCY);
        Matcher matcher = pattern.matcher(frequency);
        return matcher.matches();
    }
    /**
     * Method to validate if a given string matches the RX_ID pattern
     * @param  id
     * @return boolean
     */
    public boolean validateID(String id){
        Pattern pattern = Pattern.compile(RX_ID);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }
    /**
     * Method to validate if a given string matches the RX_HOST pattern
     * @param  host
     * @return boolean
     */
    public boolean validateHost(String host){
        Pattern pattern = Pattern.compile(RX_HOST);
        Matcher matcher = pattern.matcher(host);
        return matcher.matches();
    }

    /**
     * Method to validate if a given string matches the RX_PATH pattern
     * @param  path
     * @return boolean
     */
    public boolean validatePath(String path){
        Pattern pattern = Pattern.compile(RX_PATH);
        Matcher matcher = pattern.matcher(path);
        return matcher.matches();
    }

    /**
     * Method to validate if a given string matches the RX_PASSWORD_AUTH pattern
     * @param  password_auth
     * @return boolean
     */
    public boolean validatePassword_Auth(String password_auth){
        Pattern pattern = Pattern.compile(RX_PASSWORD_AUTH);
        Matcher matcher = pattern.matcher(password_auth);
        return matcher.matches();
    }

    //----------------------------------ParsingMethods-----------------------------------------------------------------------

    /**
     * Method to parse an augmented URL in 4 parts(id, url, min, max)
     * @param  url_augmented
     * @return ArrayList<String>
     */
    public ArrayList<String> parse_AugmentedURL(String url_augmented) {
        ArrayList<String> result = new ArrayList<>();
        if (validateAugmentedURL(url_augmented)){
            //int position;
            Pattern pattern = Pattern.compile("([^!]+)(!|$)");
            Matcher matcher = pattern.matcher(url_augmented);
            while (matcher.find()) {
                result.add(matcher.group(1));
            }
        }
        return result;
    }
    private ArrayList<String> getElementsPartFromRegex(String message, String regex,int elements){
        ArrayList<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);
        elements = matcher.groupCount();
        System.out.println(matcher.groupCount());

        if(matcher.matches()){
            for(int i=0;i < elements;i++){
                result.add(matcher.group(i+1));
            }
        }
        return result;
    }

    /**
     * Method to parse an AugmentedURL to get the url
     * @param  augmentedURL
     * @return String la url
     */
    public String parse_URLViaAugmentedURL(String augmentedURL) {
        String result = "";
        if (validateAugmentedURL(augmentedURL)){
            Pattern pattern = Pattern.compile(RX_URL);
            Matcher matcher = pattern.matcher(augmentedURL);
            if (matcher.find()) {
                result = matcher.group(0);
            }
        }
        return result;
    }
    public String parse_IdViaAugmentedURL(String augmentedURL) {
        String result = "";
        if (validateAugmentedURL(augmentedURL)){
            Pattern pattern = Pattern.compile(RX_ID);
            Matcher matcher = pattern.matcher(augmentedURL);
            if (matcher.find()) {
                result = matcher.group(0);
            }
        }
        return result;
    }
    public String parse_MinViaAugmentedURL(String augmentedURL) {
        return parse_AugmentedURL(augmentedURL).get(2);
    }
    public String parse_MaxViaAugmentedURL(String augmentedURL) {
        return parse_AugmentedURL(augmentedURL).get(3);
    }



    public List<String> parse_URLPartsViaURL(String url){
        List<String> result = new ArrayList<>();
        if (validateURL(url)){
            String isPort = "\\/";
            if (getMatcher(url, ":(\\d+)(?:/|$)").find()){
                isPort = ":";
            }
            Matcher matcher = getMatcher(url, "^(.*?):");
            if (matcher.find()) {
                result.add(matcher.group(1));
            }
            matcher = getMatcher(url, "://([a-zA-Z0-9]+)[:@]");
            if (matcher.find()) {
                result.add(matcher.group(1));
                matcher = getMatcher(url, "://[^:]+:([^@]+)@");
                if (matcher.find()) {
                    result.add(matcher.group(1));
                    matcher = getMatcher(url, "@([\\w\\.\\-]+)"+isPort);
                    if (matcher.find()) {
                        result.add(matcher.group(1));
                    }
                }else{
                    matcher = getMatcher(url, "@([\\w\\.\\-]+)"+isPort);
                    if (matcher.find()) {
                        result.add(matcher.group(1));
                    }
                }
            } else {
                matcher = getMatcher(url, "://([\\w\\.\\-]+)"+isPort);
                if (matcher.find()) {
                    result.add(matcher.group(1));
                }
            }
            matcher = getMatcher(url, ":(\\d+)(?:/|$)");
            if (matcher.find()) {
                result.add(matcher.group(1));
            }
            matcher = getMatcher(url, "([a-zA-Z0-9]+)/([a-zA-Z0-9\\.\\-_\\/]+)$");

            if (matcher.find()) {
                matcher = getMatcher(matcher.group(0),"/([a-zA-Z0-9\\.\\-_\\/]+)$");
                if(matcher.find()){
                    result.add(matcher.group(1));
                } else {
                    result.add(matcher.group(0));
                }
            } else {
                result.add("");
            }
        }
        return result;
    }

    /**
     * Method to parse a String(Password_Auth) to get a List<String> the password and the authentication if it exists,
     * else return an empty list
     * @param password_auth
     * @return List<String>{} or List<String>{password, authentication}
     */
    public List<String> parse_Password_Auth(String password_auth){
        List<String> result = new ArrayList<>();
        if (validatePassword_Auth(password_auth)){
            Matcher matcher = getMatcher(password_auth, "([^#]+)#([^#]+)");
            if (matcher.find()) {
                result.add(matcher.group(1));
                result.add(matcher.group(2));
            }
        }
        return result;
    }

    /**
     * Method to get the password part from a Password_Auth
     * @param password_auth
     * @return String password
     */
    public String getPasswordFromPassword_Auth(String password_auth){
        return parse_Password_Auth(password_auth).get(0);
    }

    /**
     * Method to get the authentication part from a Password_Auth
     * @param password_auth
     * @return
     */
    public String getAuthenticationFromPassword_Auth(String password_auth){
        return parse_Password_Auth(password_auth).get(1);
    }
    private Matcher getMatcher(String message, String regex){
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(message);
    }

    //-------------------------------------MessageParsingMethods------------------------------------------------------------


    //--------------------------------------------------------------------------------
    private ArrayList<String> getElementsFromRegex(String message, String regex, int[] elements){
        ArrayList<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);
        if(matcher.matches()){
            for(int val : elements){
                result.add(matcher.group(val));
            }
        }
        return result;
    }

}
