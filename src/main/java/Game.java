import Utils.IO;

public class Game {
    private static Map map;

    public static void main(String[] args) {
        map = new Map(20);
        map.show();
    }

//    public static void menu(){
//        boolean go = true;
//        while(go){
//            Content selectedCell = map.selectContent();
//            selectedCell.menu(); //Appel du menu de la cellule sélectionnée
//        }
//    }

    public static void menu(){
        for(int i = 0;i<4;i++){
            IO.print("Cellules, tour : "+(i+1));
            Content selectedCell = map.selectCell(); //Selection uniquement des cellules
            selectedCell.menu(); //Appel du menu de la cellule sélectionnée
        }
        for(int i = 0;i<4;i++) {
            IO.print("Virus, tour : " + (i + 1));
            Content selectedVirus = map.selectVirus(); //Selection uniquement des cellules
            selectedVirus.menu(); //Appel du menu de la cellule sélectionnée
        }
    }

    public static Map getMap() {
        return map;
    }
}
