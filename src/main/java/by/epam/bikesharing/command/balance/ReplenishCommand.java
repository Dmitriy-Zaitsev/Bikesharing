package by.epam.bikesharing.command.balance;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.exception.NotEnoughMoneyException;
import by.epam.bikesharing.exception.TransactionException;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.service.BalanceLogic;
import by.epam.bikesharing.validation.BankAccountValidation;
import by.epam.bikesharing.validation.MoneyValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class ReplenishCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ParameterName.USER);
        long userId = user.getId();
        long cardId = Long.parseLong(request.getParameter(ParameterName.CARD_ID));
        String amountString = request.getParameter(ParameterName.AMOUNT);
        if (!MoneyValidation.isValid(amountString)) {
            //TODO invalid money format
        }
        BigDecimal amount = new BigDecimal(amountString);
        if (!MoneyValidation.isNotZero(amount)) {
            //TODO the amount is too small
        }
        String cvv = request.getParameter("cvv");
        if (!BankAccountValidation.isValidCvv(cvv)) {
            //TODO invalid cvv format
        }
        try {
            session.setAttribute(ParameterName.USER, new BalanceLogic().replenish(userId, cardId, amount));
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
            //TODO user message
        } catch (TransactionException e) {
            //TODO error handling
        }
        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}