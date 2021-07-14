package demo.structure.bridge;

/**
 * @author 86134
 */
public class Bridge {
    public static void main(String[] args) {
        AbsBank bank = new ABCBank(new FixedAccount());
        bank.openAccount();
        bank = new ACBCBank(new CurrentAccount());
        bank.openAccount();
    }
}

interface Account{
    /**打开账户 @return 账户*/
    Account openAccount();
    /**显示账户类型*/
    void showAccountType();
}

class FixedAccount implements Account{

    @Override
    public Account openAccount() {
        System.out.println("打开定期账户");
        return new FixedAccount();
    }

    @Override
    public void showAccountType() {
        System.out.println("账户类型为定期账户");
    }
}

class CurrentAccount implements Account{

    @Override
    public Account openAccount() {
        System.out.println("打开活期账户");
        return new FixedAccount();
    }

    @Override
    public void showAccountType() {
        System.out.println("账户类型为活期账户");
    }
}

/**银行抽象类*/
abstract class AbsBank{
    public Account account;
    public AbsBank(Account account){
        this.account = account;
    }

    /**打开账户 @return 账户*/
    abstract Account openAccount();
}

class ABCBank extends AbsBank{
    public ABCBank(Account account){
        super(account);
    }

    @Override
    Account openAccount() {
        System.out.println("中国农业银行");
        account.openAccount();
        return account;
    }
}

class ACBCBank extends AbsBank{
    public ACBCBank(Account account){
        super(account);
    }

    @Override
    Account openAccount() {
        System.out.println("中国工商银行");
        account.openAccount();
        return account;
    }
}

