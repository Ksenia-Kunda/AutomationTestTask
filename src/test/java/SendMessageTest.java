import base.TestBase;
import db.CSVdb;
import db.MySQLdb;
import db.XMLdb;
import helper.CommonDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.HomePage;
import page.MailEntrancePage;
import page.MailPage;
import send.SendEMail;


/**
 * Created by Ksenia on 09.12.2016.
 */
public class SendMessageTest extends TestBase {

    private SendEMail sendEMail;
    private HomePage homePage;
    private MailEntrancePage mailEntrancePage;
    private MailPage mailPage;
    private SoftAssert softAssert;


    public SendMessageTest() {
        sendEMail = new SendEMail();
        softAssert = new SoftAssert();
    }

    @BeforeClass
    public void createDB(){
        XMLdb.createXML();
        CSVdb.createCSV();
    }

    @BeforeMethod
    public void setup() {
        super.setup();
        homePage = new HomePage(driver);
    }

    @Test(dataProviderClass = CommonDataProvider.class, dataProvider = "accountDataProvider")
    public void sendTest(String loginAccFrom, String passwordAccFrom, String loginAccTo, String passwordAccTo) {
        sendEMail.sendMessage(loginAccFrom, passwordAccFrom, loginAccTo);
        mailEntrancePage = homePage.goToMailEntrancePage();
        mailPage = mailEntrancePage.fillAllFields(loginAccFrom, passwordAccFrom);
        mailPage.sentFolderClick();
        softAssert.assertTrue(mailPage.findSubject(), "Sending account's subjects are not equals");

        homePage = mailPage.goToHomePage();
        homePage.logout();
        mailEntrancePage = homePage.goToMailEntrancePage();
        mailPage = mailEntrancePage.fillAllFields(loginAccTo, passwordAccTo);
        softAssert.assertTrue(mailPage.findSubject(), "Receiving account's subjects are not equals");

        softAssert.assertAll();
    }




}

