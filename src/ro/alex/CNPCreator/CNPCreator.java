package ro.alex.CNPCreator;

import sun.util.calendar.BaseCalendar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class CNPCreator {

    static int[] coduriJudet =
                    {1,//	Alba
                    2,//Arad
                    3,//Argeș
                    4,//Bacău
                    5,//Bihor
                    6,//Bistrița-Năsăud
                    7,//Botoșani
                    8,//Brașov
                    9,//Brăila
                    10,//Buzău
                    11,//Caraș-Severin
                    12,//Cluj
                    13,//Constanța
                    14,//Covasna
                    15,//Dâmbovița
                    16,//Dolj
                    17,//Galați
                    18,//Gorj
                    19,//Harghita
                    20,//Hunedoara
                    21,//Ialomița
                    22,//Iași
                    23,//Ilfov
                    24,//,"Maramureș
                    25,//Mehedinți
                    26,//Mureș
                    27,//Neamț
                    28,//Olt
                    29,//Prahova
                    30,//Satu Mare
                    31,//Sălaj
                    32,//Sibiu
                    33,//Suceava
                    34,//Teleorman
                    35,//Timiș
                    36,//Tulcea
                    37,//Vaslui
                    38,//Vâlcea
                    39,//Vrancea
                    40,//București
                    41,//București - Sector 1
                    42,//București - Sector 2
                    43,//București - Sector 3
                    44,//București - Sector 4
                    45,//București - Sector 5
                    46,//București - Sector 6
                    51,//Călărași
                    52,//Giurgiu
                    };

    static int[] controlCode = {2,7,9,1,4,6,3,5,8,2,7,9};


    public static String randomCNP(){

        BaseCalendar.Date date = new BaseCalendar.Date() {
            @Override
            public int getNormalizedYear() {
                return 0;
            }

            @Override
            public void setNormalizedYear(int i) {

            }
        };

        date.setYear(1800);
        date.addYear(20 + new Random().nextInt(290));
        date.addMonth(1 + new Random().nextInt(12));
        date.addDayOfMonth(1 + new Random().nextInt(31));

        return  generateCNP(new Random().nextBoolean(), date, coduriJudet[new Random().nextInt(coduriJudet.length)]);
    }

    public static String randomCNP(Boolean male){

        BaseCalendar.Date date = new BaseCalendar.Date() {
            @Override
            public int getNormalizedYear() {
                return 0;
            }

            @Override
            public void setNormalizedYear(int i) {

            }
        };

        date.setYear(1800);
        date.addYear(20 + new Random().nextInt(290));
        date.addMonth(1 + new Random().nextInt(12));
        date.addDayOfMonth(1 + new Random().nextInt(31));

        return  generateCNP(male, date, coduriJudet[new Random().nextInt(coduriJudet.length)]);
    }

    public static String randomCNP(Boolean male, BaseCalendar.Date date){

        return  generateCNP(male, date, coduriJudet[new Random().nextInt(coduriJudet.length)]);
    }


    public static String generateCNP(Boolean male, BaseCalendar.Date date, int codJudet){
        int[] cnp = new int[13];

        int year = date.getYear();

        System.out.println(year);

        //set year decade component
        cnp[1] = (year%100)/10;
        //set year component
        cnp[2] = year%10;

        //set gender component
        switch ((year/100)%10)
        {
            // born 1800 -> 1899
            case 8:
                if(male)
                    cnp[0] = 3;
                else
                    cnp[0] = 4;
                break;
            // born 1900 -> 1999
            case 9:
                if(male)
                    cnp[0] = 1;
                else
                    cnp[0] = 2;
                break;
            //born 2000 -> 2999
            case 0:
                if(male)
                    cnp[0] = 5;
                else
                    cnp[0] = 6;
                break;

        }

        //set month component
        int month = date.getMonth();
        //first month component
        cnp[3] = month / 10;
        //second month component
        cnp[4] = month % 10;

        // set day component
        int day = date.getDayOfMonth();
        // first day component
        cnp[5] = day / 10;
        // second day component
        cnp[6] = day % 10;

        // set county component
        // first county component
        cnp[7] = codJudet / 10;
        // second county component
        cnp[8] = codJudet % 10;

        // set randomComponent
        int random = 1 + new Random().nextInt(999);
        cnp[9] = random / 100;
        cnp[10] = (random % 100) / 10;
        cnp[11] = random % 10;

        // set control variable

        int control = 0;
        for (int i = 0; i < controlCode.length; i ++)
            control += cnp[i] * controlCode [i];

        if ((control % 11) == 10)
            cnp[12] = 1;
        else
            cnp[12] = control % 11;
        return formatCNP(cnp);
    }

    private static String formatCNP(int[] cnp){
        StringBuilder output = new StringBuilder();

        for (int i = 0; i< cnp.length; i ++)
            output.append(cnp[i]);

        return output.toString();
    }

    public static void main(String[] args)
    {
        String[] nume = {"Adelin","Adrian","Adriana","Alex","Alexandru","Alin","Amalia","Ana","Anca","Andreea","Angelica","Anghel","Anica","Aniela","Anisoara","Antoaneta","Apostol","Augustina","Aurelia","Aurica","Aurora","Barbu","Basarab","Benone","Bogdan","Brandusa","Bujor","Calin","Camelia","Camil","Carmen","Cecilia","Cezar","Ciprian","Codrut","Constanta","Constantin","Cornel","Cosmin","Costel","Costin","Craciun","Cristi","Cristina","Damian","Dan","Decebal","Denis","Denisa","Diana","Dida","Dimitrie","Dionisie","Dochia","Doina","Dorin","Doru","Dragos","Dumitru","Eduard","Elena","Emil","Eugen","Eugenia","Eusebiu","Felicia","Felix","Filoftea","Flavia","Florentina","Florina","Gabriel","Gabriela","Geanina","Gelu","Georgeta","Geta","Ghita","Gratiela","Grigore","Garalambie","Goria","Ileana","Ilie","Ioan","Ioana","Iolanda","Ion","Irina","Iurie","Lacramioara","Laur","Laura","Laurentia","Laurentiu","Lazar","Lidia","Lili(ana)","Liliana","Liviu","Loredana","Luciana","Lucretia","Lucretiu","Luminita","Madalin","Madalina","Magdalena","Manole","Manuel","Manuela","Marilena","Mihaela","","Oana","Raluca","Razvan","Ruxandra","Sorin","Valeriu"};

        StringBuilder output = new StringBuilder();
        Random random = new Random();

         for(int i = 0; i < 10; i ++){
             output.append(nume[random.nextInt(nume.length)]);
             output.append(",");
             output.append(nume[random.nextInt(nume.length)]);
             output.append(",");
             output.append(CNPCreator.randomCNP());
             output.append(System.lineSeparator());
          }

        File file = new File("dump.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(output.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
