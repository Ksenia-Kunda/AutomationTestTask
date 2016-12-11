import base.TestBase;
import db.CSVdb;
import db.XMLdb;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.HomePage;
import page.logIn.AddUserLoginPage;
import page.logIn.MailEntrancePage;
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
    private AddUserLoginPage addUserLoginPage;
    private SoftAssert softAssert;
    private static final int ACC_FROM = 2; //letter is sent from this e-mail (1 to 10 account)
    private static final int ACC_TO = 1;   // this e-mail receives e-mail (1 to 10 account)
    private static final int choiceDB = 2; // selection which DB use to get data (1 - hml, 2-csv, 3-sql)

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

    @Test
    public void sendTest() {
        sendEMail.sendMessage(ACC_FROM, ACC_TO, choiceDB);
        mailEntrancePage = homePage.goToMailEntrancePage();
        mailPage = mailEntrancePage.fillAllFields(choiceDB, ACC_FROM);
        mailPage.sentFolderClick();
        softAssert.assertTrue(mailPage.findSubject(), "First User's subjects are not equals");

        addUserLoginPage = mailPage.addUser();
        mailPage = addUserLoginPage.fillAllFields(choiceDB, ACC_TO);
        mailPage.inboxFolderClick();
        softAssert.assertTrue(mailPage.findSubject(), "Second User's subjects are not equals");

        softAssert.assertAll();
    }


}

