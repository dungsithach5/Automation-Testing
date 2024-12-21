import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import railway.model.Ticket;
import railway.model.User;
import railway.pages.BookTicketPage;
import railway.pages.HomePage;
import railway.pages.LoginPage;
import railway.pages.MyTicketPage;
import railway.types.SeatType;
import railway.types.Station;

import java.time.LocalDate;

public class CancelTicketTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    BookTicketPage bookTicketPage;
    MyTicketPage myTicketPage;
    User user;
    Ticket ticket;
    int id;

    @BeforeMethod
    public void initData() {
        loginPage = new LoginPage();
        homePage = new HomePage();
        bookTicketPage = new BookTicketPage();
        myTicketPage = new MyTicketPage();

        user = new User("test@agest.vn", "123456789");
        ticket = new Ticket(
                LocalDate.now().plusDays(5),
                Station.SAI_GON,
                Station.NHA_TRANG,
                SeatType.SOFT_SEAT,
                1
        );

        homePage.open();

        homePage.goToLoginPage();

        loginPage.login(user);

        homePage.goToBookTicketPage();

        bookTicketPage.bookTicket(ticket);

        id = bookTicketPage.getTicketID();
    }

    @Test
    public void testCancelTicket() {
        bookTicketPage.goToMyTicketPage();

        myTicketPage.cancelTicketByID(id);

        Assert.assertFalse(myTicketPage.isCancelTicketButtonDisplayed(id));
    }


}
