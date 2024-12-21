package railway.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import railway.constant.Constant;
import railway.model.Ticket;
import railway.types.SeatType;
import railway.types.Station;
import railway.utils.DriverUtils;

import java.time.Duration;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookTicketPage extends BasePage {

    By departDateSelectLocator = By.name("Date");
    By departFromSelectLocator = By.name("DepartStation");
    By arriveAtSelectLocator = By.name("ArriveStation");
    By seatTypeSelectLocator = By.name("SeatType");
    By amountSelectLocator = By.name("TicketAmount");
    By bookTicketButtonLocator = By.cssSelector("input[type=submit]");

    public void bookTicket(Ticket ticket) {
        selectDepartDate(ticket.getDepartDate());
        selectDepartFrom(ticket.getDepartFrom());
        selectArriveAt(ticket.getArriveAt());
        selectSeatType(ticket.getSeatType());
        selectAmount(ticket.getAmount());
        DriverUtils.scrollIntoView(Constant.WEBDRIVER.findElement(bookTicketButtonLocator));
        clickBookTicketButton();
    }

    public Ticket getBookedTicket() {
        return new Ticket(
                getDepartDate(),
                getDepartStation(),
                getArriveStation(),
                getSeatType(),
                getAmount()
        );
    }

    public int getTicketID() {
        String url = Constant.WEBDRIVER.getCurrentUrl();
        Pattern p = Pattern.compile("(?<=id=)\\d+");
        Matcher matcher = p.matcher(url);
        matcher.find();
        return Integer.parseInt(matcher.group(0));
    }

    private Station getDepartStation() {
        return Station.fromText(getColumnText("Depart Station"));
    }

    private Station getArriveStation() {
        return Station.fromText(getColumnText("Arrive Station"));
    }

    private LocalDate getDepartDate() {
        return LocalDate.parse(getColumnText("Depart Date"), Constant.FORMATTER);
    }

    private SeatType getSeatType() {
        return SeatType.fromText(getColumnText("Seat Type"));
    }

    private int getAmount() {
        return Integer.parseInt(getColumnText("Amount"));
    }

    private String getColumnText(String columnName) {
        By cell = By.xpath(String.format("//tr/td[%d]", getColumnIndex(columnName)));
        return Constant.WEBDRIVER.findElement(cell).getText();
    }

    private int getColumnIndex(String column) {
        return Constant.WEBDRIVER.findElements(By.xpath(String.format("//tr/th[text()='%s']/preceding-sibling::th", column))).size() + 1;
    }

    private void selectDepartDate(LocalDate date) {
        Select select = new Select(Constant.WEBDRIVER.findElement(departDateSelectLocator));
        select.selectByVisibleText(date.format(Constant.FORMATTER));
    }

    private void selectDepartFrom(Station station) {
        Select select = new Select(Constant.WEBDRIVER.findElement(departFromSelectLocator));
        if (select.getFirstSelectedOption().getText().equals(station.getText())) {
            return;
        }
        select.selectByVisibleText(station.getText());
        // waiting for arrive at dropdown load new data
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.stalenessOf(Constant.WEBDRIVER.findElement(arriveAtSelectLocator)));
    }

    private void selectArriveAt(Station station) {
        Select select = new Select(Constant.WEBDRIVER.findElement(arriveAtSelectLocator));
        select.selectByVisibleText(station.getText());
    }

    private void selectSeatType(SeatType type) {
        Select select = new Select(Constant.WEBDRIVER.findElement(seatTypeSelectLocator));
        select.selectByVisibleText(type.getText());
    }

    private void selectAmount(int amount) {
        Select select = new Select(Constant.WEBDRIVER.findElement(amountSelectLocator));
        select.selectByVisibleText(String.valueOf(amount));
    }

    private void clickBookTicketButton() {
        Constant.WEBDRIVER.findElement(bookTicketButtonLocator).click();
    }
}
