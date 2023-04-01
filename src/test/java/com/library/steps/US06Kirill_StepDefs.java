package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.LoginPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class US06Kirill_StepDefs {

    LoginPage loginPage = new LoginPage();
    BookPage bookPage = new BookPage();
    String bookName;
    String author;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String string) {
        loginPage.login(string);
    }
    @Given("the user navigates to {string} page")
    public void the_user_navigates_to_page(String string) {
        bookPage.navigateModule(string);
    }
    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        bookPage.addBook.click();
    }
    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String bookName) {
        bookPage.bookName.sendKeys(bookName);
        this.bookName = bookName;
    }
    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String string) {
        bookPage.isbn.sendKeys(string);
    }
    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String string) {
        bookPage.year.sendKeys(string);
    }
    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String author) {
        bookPage.author.sendKeys(author);
        this.author = author;
    }
    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String string) {
        Select select = new Select(bookPage.categoryDropdown);
        select.selectByVisibleText(string);

    }
    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
        bookPage.saveChanges.click();
    }
    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String string) {
        bookPage.toastMessage.isDisplayed();
    }
    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String string) {
        String query = "select id,name,author from books\n" +
                "where name = '"+bookName+"' and author='"+author+"'\n" +
                "order by id desc;";
        DB_Util.runQuery(query);
        List<String> expectedBook = new ArrayList<>();
        expectedBook.addAll(Arrays.asList(bookName, author));

        List<String> actualBook = new ArrayList<>();
        actualBook.addAll(Arrays.asList(DB_Util.getCellValue(1,2),
                DB_Util.getCellValue(1,3)));

        Assert.assertEquals(expectedBook, actualBook);
    }
}
