import java.util.Random;

/**
 * Created by Maurice on 12/8/2014.
 */
public class Politician {
    private String name;
    private int region;
    private String party;
    private String seat;
    private String number;
    private String email;
    private String[] stance;

    public Politician(String line) {
        String[] splitArr = line.split(";");
        region = Integer.valueOf(splitArr[0]);
        name = splitArr[1];
        if(splitArr[2].equals("D"))
            party = "Democrat";
        else
            party = "Republican";
        seat = splitArr[3];
        number = splitArr[4];
        email = splitArr[5];
        stance = new String[24];
        generateStances();
    }

    private String randomizeStance() {
        String[] array = {"Pro","Anti","Neutral","N/A"};
        return array[new Random().nextInt(array.length)];
    }

    private void generateStances() {
        for(int i = 0; i < 24 ; i++){
            stance[i] = randomizeStance();
        }
    }

    public String export2sql(){
        String template = "INSERT INTO Michigan VALUES(";
        template += Integer.toString(region) ;
        template += ",'" +name+ "',";
        template += "'" +party+ "',";
        template += "'" +seat+ "',";
        template += "'" +number+ "',";
        template += "'" +email+ "',";

        for(String s : stance) {
            template += "'"+s+"',";
        }
        template = template.substring(0,template.lastIndexOf(","));
        template += ");";
        System.out.println(template);
        return template;
    }
}
