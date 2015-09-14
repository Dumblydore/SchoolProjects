/**
 * Created by Maurice on 12/10/2014.
 */
public class PhotoLinks {
    static String genLink(int num) {
        String template = "http://media.housedems.com/dynaimg-files/picture.jpg/district-$num.jpg";
        return template.replace("$num",String.format("%03d",num));
    }
}
