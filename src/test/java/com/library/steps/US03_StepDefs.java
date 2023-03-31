package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US03_StepDefs {

    LoginPage loginPage;
    DashBoardPage dashBoardPage;
    BookPage bookPage;
    List<String> actualCategoriesList;
    List<String> expectedCategoriesList;


    @Given("the {string} on the home page")
    public void the_on_the_home_page(String librarian) {
        loginPage = new LoginPage();
        loginPage.login(librarian);
        BrowserUtil.waitFor(2);

    }


    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String books) {
        BrowserUtil.waitFor(2);
        //new DashBoardPage().navigateModule(books);
        dashBoardPage = new DashBoardPage();
        dashBoardPage.navigateModule(books);

    }

    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {
        BrowserUtil.waitFor(2);

        actualCategoriesList = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        System.out.println("actualCategoryList = " + actualCategoriesList);
        actualCategoriesList.remove(0);
        System.out.println("------- AFTER REMOVE FIRST ONE --------");
        System.out.println("actualCategoryList = " + actualCategoriesList);

    }

    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {

        String query = "Select name from book_categories";
        DB_Util.runQuery(query);
        expectedCategoriesList = DB_Util.getColumnDataAsList(1);
        BrowserUtil.waitFor(2);
        System.out.println("expectedCategoriesList = " + expectedCategoriesList);

        Assert.assertEquals(expectedCategoriesList, actualCategoriesList);
    }
}
