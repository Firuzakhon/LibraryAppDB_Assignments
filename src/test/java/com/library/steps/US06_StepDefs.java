package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class US06_StepDefs {

    BookPage bookPage;
    LoginPage loginPage;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String user) {
        loginPage=new LoginPage();
        loginPage.login(user);
        BrowserUtil.waitFor(3);
    }

    @Given("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        bookPage = new BookPage();
        bookPage.navigateModule(moduleName);
        BrowserUtil.waitFor(5);
    }

    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        bookPage.addBook.click();
    }

    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String name) {
        bookPage.bookName.sendKeys(name);

    }

    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String isbn) {
        bookPage.isbn.sendKeys(isbn);
    }

    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String year) {
        bookPage.year.sendKeys(year);
    }

    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String author) {
        bookPage.author.sendKeys(author);
    }

    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String category) {
        BrowserUtil.selectOptionDropdown(bookPage.categoryDropdown,category);
    }

    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
        bookPage.saveChanges.click();
        BrowserUtil.waitFor(9);
    }

    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String expectedMessage) {
        String actualMessage = bookPage.toastMessage.getText();
        Assert.assertEquals(expectedMessage,actualMessage);
        BrowserUtil.waitFor(5);


    }

    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String expectedBookName) {
        String query = "select name, author, isbn from books\n" +
                "where name = '"+expectedBookName+"'";

        DB_Util.runQuery(query);

        Map<String, String> rowMap = DB_Util.getRowMap(1);

        String actualBookName = rowMap.get("name");

        Assert.assertEquals(expectedBookName,actualBookName);
    }



}
