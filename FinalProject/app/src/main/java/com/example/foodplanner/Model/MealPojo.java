package com.example.foodplanner.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "meal_table")
public class MealPojo implements Serializable {
    @NonNull
    @PrimaryKey
    public String strMeal;
    public String idMeal;

   // public Object strDrinkAlternate;
    public String strCategory;
    public String strArea;
    public String strInstructions;
    public String strMealThumb;
    //public Object strTags;
    public String strYoutube;
    public String strIngredient1;
    public String strIngredient2;
    public String strIngredient3;
    public String strIngredient4;
    public String strIngredient5;
    public String strIngredient6;
    public String strIngredient7;
    public String strIngredient8;
    public String strIngredient9;
    public String strIngredient10;
    public String strIngredient11;
    public String strIngredient12;
    public String strIngredient13;
    public String strIngredient14;
    public String strIngredient15;
    public String strIngredient16;
    public String strIngredient17;
    public String strIngredient18;
    public String strIngredient19;
    public String strIngredient20;
    public String strMeasure1;
    public String strMeasure2;
    public String strMeasure3;
    public String strMeasure4;
    public String strMeasure5;
    public String strMeasure6;
    public String strMeasure7;
    public String strMeasure8;
    public String strMeasure9;
    public String strMeasure10;
    public String strMeasure11;
    public String strMeasure12;
    public String strMeasure13;
    public String strMeasure14;
    public String strMeasure15;
    public String strMeasure16;
    public String strMeasure17;
    public String strMeasure18;
    public String strMeasure19;
    public String strMeasure20;
    public String strSource;

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }


    public String getFlagUrl() {
        String countryCode = getCountryCode(this.strArea);
        return "https://flagcdn.com/160x120/" + countryCode + ".png";
    }

  /*  public String getIngredientUrl(){
        return "https://www.themealdb.com/images/ingredients/" + strIngredient +".png";
    }*/

    private String getCountryCode(String countryName) {
        // Map country names to their codes
        switch (countryName.toLowerCase()) {
            case "american":
                return "us";
            case "british":
                return "gb";
            case "canadian":
                return "ca";
            case "chinese":
                return "cn";
            case "croatian":
                return "hr";
            case "dutch":
                return "nl";
            case "egyptian":
                return "eg";
            case "filipino":
                return "ph";
            case "french":
                return "fr";
            case "greek":
                return "gr";
            case "indian":
                return "in";
            case "irish":
                return "ie";
            case "italian":
                return "it";
            case "jamaican":
                return "jm";
            case "japanese":
                return "jp";
            case "kenyan":
                return "ke";
            case "malaysian":
                return "my";
            case "mexican":
                return "mx";
            case "moroccan":
                return "ma";
            case "polish":
                return "pl";
            case "portuguese":
                return "pt";
            case "russian":
                return "ru";
            case "spanish":
                return "es";
            case "thai":
                return "th";
            case "tunisian":
                return "tn";
            case "turkish":
                return "tr";
            case "ukrainian":
                return "ua";
            case "unknown":
                return ""; // For unknown, we can return an empty string or a default value
            case "vietnamese":
                return "vn";
            default:
                return ""; // Default case for unmapped countries
        }
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public String getStrIngredient2() {
        return strIngredient2;
    }

    public void setStrIngredient2(String strIngredient2) {
        this.strIngredient2 = strIngredient2;
    }

    public String getStrIngredient3() {
        return strIngredient3;
    }

    public void setStrIngredient3(String strIngredient3) {
        this.strIngredient3 = strIngredient3;
    }

    public String getStrIngredient4() {
        return strIngredient4;
    }

    public void setStrIngredient4(String strIngredient4) {
        this.strIngredient4 = strIngredient4;
    }

    public String getStrIngredient5() {
        return strIngredient5;
    }

    public void setStrIngredient5(String strIngredient5) {
        this.strIngredient5 = strIngredient5;
    }

    public String getStrIngredient6() {
        return strIngredient6;
    }

    public void setStrIngredient6(String strIngredient6) {
        this.strIngredient6 = strIngredient6;
    }

    public String getStrIngredient7() {
        return strIngredient7;
    }

    public void setStrIngredient7(String strIngredient7) {
        this.strIngredient7 = strIngredient7;
    }

    public String getStrIngredient9() {
        return strIngredient9;
    }

    public void setStrIngredient9(String strIngredient9) {
        this.strIngredient9 = strIngredient9;
    }

    public String getStrIngredient8() {
        return strIngredient8;
    }

    public void setStrIngredient8(String strIngredient8) {
        this.strIngredient8 = strIngredient8;
    }

    public String getStrIngredient10() {
        return strIngredient10;
    }

    public void setStrIngredient10(String strIngredient10) {
        this.strIngredient10 = strIngredient10;
    }

    public String getStrIngredient11() {
        return strIngredient11;
    }

    public void setStrIngredient11(String strIngredient11) {
        this.strIngredient11 = strIngredient11;
    }

    public String getStrIngredient12() {
        return strIngredient12;
    }

    public void setStrIngredient12(String strIngredient12) {
        this.strIngredient12 = strIngredient12;
    }

    public String getStrIngredient13() {
        return strIngredient13;
    }

    public void setStrIngredient13(String strIngredient13) {
        this.strIngredient13 = strIngredient13;
    }

    public String getStrIngredient14() {
        return strIngredient14;
    }

    public void setStrIngredient14(String strIngredient14) {
        this.strIngredient14 = strIngredient14;
    }

    public String getStrIngredient15() {
        return strIngredient15;
    }

    public void setStrIngredient15(String strIngredient15) {
        this.strIngredient15 = strIngredient15;
    }

    public String getStrIngredient16() {
        return strIngredient16;
    }

    public void setStrIngredient16(String strIngredient16) {
        this.strIngredient16 = strIngredient16;
    }

    public String getStrIngredient17() {
        return strIngredient17;
    }

    public void setStrIngredient17(String strIngredient17) {
        this.strIngredient17 = strIngredient17;
    }

    public String getStrIngredient18() {
        return strIngredient18;
    }

    public void setStrIngredient18(String strIngredient18) {
        this.strIngredient18 = strIngredient18;
    }

    public String getStrIngredient19() {
        return strIngredient19;
    }

    public void setStrIngredient19(String strIngredient19) {
        this.strIngredient19 = strIngredient19;
    }

    public String getStrIngredient20() {
        return strIngredient20;
    }

    public void setStrIngredient20(String strIngredient20) {
        this.strIngredient20 = strIngredient20;
    }

    public String getStrMeasure1() {
        return strMeasure1;
    }

    public void setStrMeasure1(String strMeasure1) {
        this.strMeasure1 = strMeasure1;
    }

    public String getStrMeasure2() {
        return strMeasure2;
    }

    public void setStrMeasure2(String strMeasure2) {
        this.strMeasure2 = strMeasure2;
    }

    public String getStrMeasure3() {
        return strMeasure3;
    }

    public void setStrMeasure3(String strMeasure3) {
        this.strMeasure3 = strMeasure3;
    }

    public String getStrMeasure4() {
        return strMeasure4;
    }

    public void setStrMeasure4(String strMeasure4) {
        this.strMeasure4 = strMeasure4;
    }

    public String getStrMeasure5() {
        return strMeasure5;
    }

    public void setStrMeasure5(String strMeasure5) {
        this.strMeasure5 = strMeasure5;
    }

    public String getStrMeasure6() {
        return strMeasure6;
    }

    public void setStrMeasure6(String strMeasure6) {
        this.strMeasure6 = strMeasure6;
    }

    public String getStrMeasure7() {
        return strMeasure7;
    }

    public void setStrMeasure7(String strMeasure7) {
        this.strMeasure7 = strMeasure7;
    }

    public String getStrMeasure8() {
        return strMeasure8;
    }

    public void setStrMeasure8(String strMeasure8) {
        this.strMeasure8 = strMeasure8;
    }

    public String getStrMeasure9() {
        return strMeasure9;
    }

    public void setStrMeasure9(String strMeasure9) {
        this.strMeasure9 = strMeasure9;
    }

    public String getStrMeasure10() {
        return strMeasure10;
    }

    public void setStrMeasure10(String strMeasure10) {
        this.strMeasure10 = strMeasure10;
    }

    public String getStrMeasure11() {
        return strMeasure11;
    }

    public void setStrMeasure11(String strMeasure11) {
        this.strMeasure11 = strMeasure11;
    }

    public String getStrMeasure12() {
        return strMeasure12;
    }

    public void setStrMeasure12(String strMeasure12) {
        this.strMeasure12 = strMeasure12;
    }

    public String getStrMeasure13() {
        return strMeasure13;
    }

    public void setStrMeasure13(String strMeasure13) {
        this.strMeasure13 = strMeasure13;
    }

    public String getStrMeasure14() {
        return strMeasure14;
    }

    public void setStrMeasure14(String strMeasure14) {
        this.strMeasure14 = strMeasure14;
    }

    public String getStrMeasure15() {
        return strMeasure15;
    }

    public void setStrMeasure15(String strMeasure15) {
        this.strMeasure15 = strMeasure15;
    }

    public String getStrMeasure16() {
        return strMeasure16;
    }

    public void setStrMeasure16(String strMeasure16) {
        this.strMeasure16 = strMeasure16;
    }

    public String getStrMeasure17() {
        return strMeasure17;
    }

    public void setStrMeasure17(String strMeasure17) {
        this.strMeasure17 = strMeasure17;
    }

    public String getStrMeasure18() {
        return strMeasure18;
    }

    public void setStrMeasure18(String strMeasure18) {
        this.strMeasure18 = strMeasure18;
    }

    public String getStrMeasure19() {
        return strMeasure19;
    }

    public void setStrMeasure19(String strMeasure19) {
        this.strMeasure19 = strMeasure19;
    }

    public String getStrMeasure20() {
        return strMeasure20;
    }

    public void setStrMeasure20(String strMeasure20) {
        this.strMeasure20 = strMeasure20;
    }

    public String getStrSource() {
        return strSource;
    }

    public void setStrSource(String strSource) {
        this.strSource = strSource;
    }
//
//    public Object getStrImageSource() {
//        return strImageSource;
//    }
//
//    public void setStrImageSource(Object strImageSource) {
//        this.strImageSource = strImageSource;
//    }

    /* For get The measures of Ingriendents */

    public ArrayList<String> getIngredients() {
        ArrayList<String> ingredients = new ArrayList<>();
        if (getStrIngredient1() != null && !getStrIngredient1().equals("")) {
            ingredients.add(getStrIngredient1());
        }
        if (getStrIngredient2() != null && !getStrIngredient2().equals("")) {
            ingredients.add(getStrIngredient2());
        }
        if (getStrIngredient3() != null && !getStrIngredient3().equals("")) {
            ingredients.add(getStrIngredient3());
        }
        if (getStrIngredient4() != null && !getStrIngredient4().equals("")) {
            ingredients.add(getStrIngredient4());
        }
        if (getStrIngredient5() != null && !getStrIngredient5().equals("")) {
            ingredients.add(getStrIngredient5());
        }
        if (getStrIngredient6() != null && !getStrIngredient6().equals("")) {
            ingredients.add(getStrIngredient6());
        }
        if (getStrIngredient7() != null && !getStrIngredient7().equals("")) {
            ingredients.add(getStrIngredient7());
        }
        if (getStrIngredient8() != null && !getStrIngredient8().equals("")) {
            ingredients.add(getStrIngredient8());
        }
        if (getStrIngredient9() != null && !getStrIngredient9().equals("")) {
            ingredients.add(getStrIngredient9());
        }
        if (getStrIngredient10() != null && !getStrIngredient10().equals("")) {
            ingredients.add(getStrIngredient10());
        }
        if (getStrIngredient11() != null && !getStrIngredient11().equals("")) {
            ingredients.add(getStrIngredient11());
        }
        if (getStrIngredient12() != null && !getStrIngredient12().equals("")) {
            ingredients.add(getStrIngredient12());
        }
        if (getStrIngredient13() != null && !getStrIngredient13().equals("")) {
            ingredients.add(getStrIngredient13());
        }
        if (getStrIngredient14() != null && !getStrIngredient14().equals("")) {
            ingredients.add(getStrIngredient14());
        }
        if (getStrIngredient15() != null && !getStrIngredient15().equals("")) {
            ingredients.add(getStrIngredient15());
        }
        if (getStrIngredient16() != null && !getStrIngredient16().equals("")) {
            ingredients.add(getStrIngredient16());
        }
        if (getStrIngredient17() != null && !getStrIngredient17().equals("")) {
            ingredients.add(getStrIngredient17());
        }
        if (getStrIngredient18() != null && !getStrIngredient18().equals("")) {
            ingredients.add(getStrIngredient18());
        }
        if (getStrIngredient19() != null && !getStrIngredient19().equals("")) {
            ingredients.add(getStrIngredient19());
        }
        if (getStrIngredient20() != null && !getStrIngredient20().equals("")) {
            ingredients.add(getStrIngredient20());
        }
        return ingredients;
    }
    public ArrayList<String> getMeasures() {
        ArrayList<String> Measures = new ArrayList<>();
        if (getStrMeasure1() != null && !getStrMeasure1().equals("")) {
            Measures.add(getStrMeasure1());
        }
        if (getStrMeasure2() != null && !getStrMeasure2().equals("")) {
            Measures.add(getStrMeasure2());
        }
        if (getStrMeasure3() != null && !getStrMeasure3().equals("")) {
            Measures.add(getStrMeasure3());
        }
        if (getStrMeasure4() != null && !getStrMeasure4().equals("")) {
            Measures.add(getStrMeasure4());
        }
        if (getStrMeasure5() != null && !getStrMeasure5().equals("")) {
            Measures.add(getStrMeasure5());
        }
        if (getStrMeasure6() != null && !getStrMeasure6().equals("")) {
            Measures.add(getStrMeasure6());
        }
        if (getStrMeasure7() != null && !getStrMeasure7().equals("")) {
            Measures.add(getStrMeasure7());
        }
        if (getStrMeasure8() != null && !getStrMeasure8().equals("")) {
            Measures.add(getStrMeasure8());
        }
        if (getStrMeasure9() != null && !getStrMeasure9().equals("")) {
            Measures.add(getStrMeasure9());
        }
        if (getStrMeasure10() != null && !getStrMeasure10().equals("")) {
            Measures.add(getStrMeasure10());
        }
        if (getStrMeasure11() != null && !getStrMeasure11().equals("")) {
            Measures.add(getStrMeasure11());
        }
        if (getStrMeasure12() != null && !getStrMeasure12().equals("")) {
            Measures.add(getStrMeasure12());
        }
        if (getStrMeasure13() != null && !getStrMeasure13().equals("")) {
            Measures.add(getStrMeasure13());
        }
        if (getStrMeasure14() != null && !getStrMeasure14().equals("")) {
            Measures.add(getStrMeasure14());
        }
        if (getStrMeasure15() != null && !getStrMeasure15().equals("")) {
            Measures.add(getStrMeasure15());
        }
        if (getStrMeasure16() != null && !getStrMeasure16().equals("")) {
            Measures.add(getStrMeasure16());
        }
        if (getStrMeasure17() != null && !getStrMeasure17().equals("")) {
            Measures.add(getStrMeasure17());
        }
        if (getStrMeasure18() != null && !getStrMeasure18().equals("")) {
            Measures.add(getStrMeasure18());
        }
        if (getStrMeasure19() != null && !getStrMeasure19().equals("")) {
            Measures.add(getStrMeasure19());
        }
        if (getStrMeasure20() != null && !getStrMeasure20().equals("")) {
            Measures.add(getStrMeasure20());
        }
        return Measures;
    }
}
