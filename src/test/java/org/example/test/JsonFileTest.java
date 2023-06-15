package org.example.test;

import org.example.model.Detective;
import org.example.model.DetectiveCategory;
import org.example.model.Extra;
import org.example.model.JsonExample;
import org.example.util.FileParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class JsonFileTest {

    private final String filePath = System.getProperty("user.dir") + "/src/test/resources/files/positive_file.json";
    private final String schemaPath = System.getProperty("user.dir") + "/src/test/resources/files/schema.json";

    private JsonExample jsonActual;

    @BeforeClass
    public void readJsonFromFile() {
        jsonActual = new FileParser().readFileContentAsJsonPOJO(filePath);
    }

    @Test(description = "check checkDetectivesSize",
            groups = {"positive"})
    public void checkDetectivesSize() {
        Assert.assertTrue(jsonActual.detectives.size() >= 1
                        &&
                        jsonActual.detectives.size() <= 3,
                "detectives.size() should be 1..3, found: " + jsonActual.detectives.size());
    }

    @Test(description = "check checkMainId",
            groups = {"positive"})
    public void checkMainId() {
        List<Integer> mainIdList = new ArrayList<>();
        for (int i = 0; i < jsonActual.detectives.size(); i++) {
            mainIdList.add(jsonActual.detectives.get(i).mainId);
        }
        Assert.assertTrue(mainIdList.stream().allMatch(id -> id >= 0 && id <= 10));
    }

    @Test(description = "check checkCategoryId",
            groups = {"positive"})
    public void checkCategoryId() {
        List<Integer> mainIdList = new ArrayList<>();
        for (int i = 0; i < jsonActual.detectives.size(); i++) {
            mainIdList.add(jsonActual.detectives.get(i).categories.get(0).categoryID);
        }
        Assert.assertTrue(mainIdList.stream().allMatch(id -> id == 1 || id == 2));
    }

    @Test(description = "check checkExtraNotNullCategoryId1",
            groups = {"positive"})
    public void checkExtraNotNullCategoryId1() {
        boolean isNullForCategory1 = false;
        for (int i = 0; i < jsonActual.detectives.size(); i++) {
            if (jsonActual.detectives.get(i).categories.get(0).categoryID == 1
                    &&
                    jsonActual.detectives.get(i).categories.get(0).extra == null
            ) {
                isNullForCategory1 = true;
            }
        }
        Assert.assertFalse(isNullForCategory1, "Extra is null for CategoryId = 1");
    }

    @Test(description = "check checkExtraArrayNotEmptyCategoryId1",
            groups = {"positive"})
    public void checkExtraArrayNotEmptyCategoryId1() {
        boolean isEmptyForCategory1 = false;
        for (int i = 0; i < jsonActual.detectives.size(); i++) {
            if (jsonActual.detectives.get(i).categories.get(0).categoryID == 1
                    &&
                    jsonActual.detectives.get(i).categories.get(0).extra != null
                    &&
                    jsonActual.detectives.get(i).categories.get(0).extra.extraArray.isEmpty()
            ) {
                isEmptyForCategory1 = true;
            }
        }
        Assert.assertFalse(isEmptyForCategory1, "ExtraArray is empty for CategoryId = 1");
    }

    @Test(description = "check checkSuccessValue",
            groups = {"positive"})
    public void checkSuccessValue() {
        boolean isSherlock = false;
        for (int i = 0; i < jsonActual.detectives.size(); i++) {
            if (jsonActual.detectives.get(i).firstName.equals("Sherlock")) {
                isSherlock = true;
            }
        }
        Assert.assertTrue(isSherlock == jsonActual.success,
                "Success for Sherlock: ");
    }

    @Test(description = "check checkNegativeDetectivesSizes",
            groups = {"negative"})
    public void checkNegativeDetectivesSizes() {
        SoftAssert softAssert = new SoftAssert();
        int[] detectivesSizes = {0, 4, 5};

        JsonExample jsonExample = new JsonExample();
        for (int size : detectivesSizes) {
            List<Detective> list = new ArrayList<>();
            for (int i = 1; i <= size; i++) {
                list.add(Detective.builder().mainId(i).build());
            }
            jsonExample.setDetectives(list);
            // should write json file here and read it back with FileParser


            softAssert.assertFalse(jsonExample.detectives.size() >= 1
                            &&
                            jsonExample.detectives.size() <= 3,
                    "detectives.size() should be 1..3, found: " + jsonExample.detectives.size());
        }

        softAssert.assertAll();
    }

    @Test(description = "check checkNegativeMainId",
            groups = {"negative"})
    public void checkNegativeMainId() {
        SoftAssert softAssert = new SoftAssert();
        int[] mainId = {-1, 0, 11, 100};

        JsonExample jsonExample = new JsonExample();
        for (int id : mainId) {
            List<Detective> list = new ArrayList<>();
            list.add(Detective.builder().mainId(id).build());
            jsonExample.setDetectives(list);
            // should write json file here and read it back with FileParser

            softAssert.assertFalse(jsonExample.detectives.get(0).mainId >= 1
                            &&
                            jsonExample.detectives.get(0).mainId <= 3,
                    "mainId should be as");
        }

        softAssert.assertAll();
    }

    @Test(description = "check checkNegativeCategoryId",
            groups = {"negative"})
    public void checkNegativeCategoryId() {
        SoftAssert softAssert = new SoftAssert();
        int[] categoryId = {-1, 0, 3, 4};

        JsonExample jsonExample = new JsonExample();
        for (int id : categoryId) {
            List<Detective> list = new ArrayList<>();
            list.add(Detective
                    .builder()
                    .categories(List.of(DetectiveCategory
                            .builder()
                            .categoryID(id)
                            .build()))
                    .build());
            jsonExample.setDetectives(list);
            // should write json file here and read it back with FileParser

            softAssert.assertFalse(jsonExample.detectives.get(0).categories.get(0).categoryID == 1
                            ||
                            jsonExample.detectives.get(0).categories.get(0).categoryID == 2,
                    "categoryId should be 1..2 ");
        }

        softAssert.assertAll();
    }

    @Test(description = "check checkNegativeExtraNotNullCategoryId1",
            groups = {"negative"})
    public void checkNegativeExtraNotNullCategoryId1() {
        SoftAssert softAssert = new SoftAssert();
        int[] categoryId = {1, 2};

        JsonExample jsonExample = new JsonExample();
        for (int id : categoryId) {
            List<Detective> list = new ArrayList<>();
            list.add(Detective
                    .builder()
                    .categories(List.of(DetectiveCategory
                            .builder()
                            .categoryID(id)
                            .extra(null)
                            .build()))
                    .build());
            jsonExample.setDetectives(list);
            // should write json file here and read it back with FileParser

            softAssert.assertFalse(jsonExample.detectives.get(0).categories.get(0).categoryID == 1
                            &&
                            jsonExample.detectives.get(0).categories.get(0).extra != null,
                    "Extra shouldn't be null for categoryId = 1 ");
        }

        softAssert.assertAll();
    }

    @Test(description = "check checkNegativeExtraArrayNotEmptyCategoryId1",
            groups = {"negative"})
    public void checkNegativeExtraArrayNotEmptyCategoryId1() {

        JsonExample jsonExample = new JsonExample();
        List<Detective> list = new ArrayList<>();
        list.add(Detective
                .builder()
                .categories(List.of(DetectiveCategory
                        .builder()
                        .categoryID(1)
                        .extra(Extra.builder().extraArray(List.of()).build())
                        .build()))
                .build());
        jsonExample.setDetectives(list);
        // should write json file here and read it back with FileParser

        Assert.assertTrue(jsonExample.detectives.get(0).categories.get(0).categoryID == 1
                        &&
                        jsonExample.detectives.get(0).categories.get(0).extra.extraArray.isEmpty(),
                "Extra shouldn't be empty for categoryId = 1 ");
    }

    @Test(description = "check checkNegativeSuccessValue",
            groups = {"negative"})
    public void checkNegativeSuccessValue() {

        JsonExample jsonExample = new JsonExample();
        List<Detective> list = new ArrayList<>();
        list.add(Detective
                .builder()
                .firstName("Sherlock")
                .build());
        jsonExample.setDetectives(list);
        jsonExample.setSuccess(false);
        // should write json file here and read it back with FileParser

        Assert.assertFalse(jsonExample.success,
                "Success value should be true for Sherlock");
    }

}
