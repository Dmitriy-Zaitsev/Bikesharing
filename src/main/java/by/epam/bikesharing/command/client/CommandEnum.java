package by.epam.bikesharing.command.client;

import by.epam.bikesharing.command.*;
import by.epam.bikesharing.command.balance.AddCardCommand;
import by.epam.bikesharing.command.balance.CardsPageCommand;
import by.epam.bikesharing.command.balance.ReplenishCommand;
import by.epam.bikesharing.command.balance.ReplenishPageCommand;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    SEARCH {
        {
            this.command = new SearchCommand();
        }
    },
    SIGNUP {
        {
            this.command = new SignupCommand();
        }
    },
    REGISTER {
        {
            this.command = new RegisterCommand();
        }
    },
    VERIFY {
        {
            this.command = new VerifyCommand();
        }
    },
    PROFILE {
        {
            this.command = new ProfileCommand();
        }
    },
    ADD_CARD {
        {
            this.command = new AddCardCommand();
        }
    },
    CARDS_PAGE {
        {
            this.command = new CardsPageCommand();
        }
    },
    REPLENISH {
        {
            this.command = new ReplenishCommand();
        }
    },
    REPLENISH_PAGE {
        {
            this.command = new ReplenishPageCommand();
        }
    },
    RENT {
        {
            this.command = new RentCommand();
        }
    },
    FINISH_RENT {
        {
            this.command = new FinishRentCommand();
        }
    },
    SAVE_PROFILE {
        {
            this.command = new SaveProfileCommand();
        }
    },
    MAIN_PAGE {
        {
            this.command = new MainPageCommand();
        }
    },
    BIKES_PAGE {
        {
            this.command = new BikesPageCommand();
        }
    },
    USERS_PAGE {
        {
            this.command = new UsersPageCommand();
        }
    },
    RENTS_PAGE {
        {
            this.command = new RentsPageCommand();
        }
    },
    MODELS_PAGE {
        {
            this.command = new ModelsPageCommand();
        }
    },
    ADD_MODEL {
        {
            this.command = new AddModelCommand();
        }
    },
    CHANGE_COST {
        {
            this.command = new ChangeCostCommand();
        }
    },
    ADD_BIKE {
        {
            this.command = new AddBikeCommand();
        }
    },
    CHANGE_SPOT {
        {
            this.command = new ChangeSpotCommand();
        }
    },
    LOCALIZATION {
        {
            this.command = new LocalizationCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}