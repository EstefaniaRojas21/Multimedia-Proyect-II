package co.edu.uptc.run;

public class AppRun {
    public static void main(String[] args) {

        AppMenus menus = new AppMenus();
        int op = 0;
        while (op != -1) {
            op = switch (op) {
                case 0 -> menus.principalMenu(op);
                case 1 -> menus.logInMenu(op);
                case 2 -> menus.registerMenu(op);
                case 3 -> menus.awayMenu(op);
                case 4 -> menus.administratorMenu(op);
                case 5 -> menus.moviesManagementMenu(op);
                case 6 -> menus.ShowMoviesMenu(op);
                case 7 -> menus.createMoviesMenu(op);
                case 8 -> menus.updateMoviesMenu(op);
                case 9 -> menus.deleteMovieMenu(op);
                case 10 -> menus.SeriesManagementMenu(op);
                case 11 -> menus.createSerieMenu(op);
                case 12 -> menus.ShowSeriesMenu(op);
                case 13 -> menus.updateSeriesMenu(op);
                case 14 -> menus.deleteSeriesMenu(op);
                case 20 -> menus.userRegisteredMenu(op);
                case 21 -> menus.ShowMovies(op);
                case 22 -> menus.ShowSeries(op);
                case 25 -> menus.subscriptionManagerMenu(op);
                case 26 -> menus.seeSubscriptionsMenu();
                case 27 -> menus.createSubscriptionsMenu(op);
                case 28 -> menus.updatesubscriptionMenu(op);
                case 29 -> menus.removeSubscriptionMenu(op);
                case 30 -> menus.playListMenu(op);
                case 31 -> menus.seePlayListMenu(op);
                case 32 -> menus.createPlayListMenu(op);
                case 33 -> menus.updatePlayListMenu(op);
                case 34 -> menus.removePlayListMenu(op);
                case 35 -> menus.categoryMenu(op);
                case 36 -> menus.seeCategorytMenu(op);
                case 37 -> menus.createCategoryMenu(op);
                case 38 -> menus.updateCategoryMenu(op);
                case 39 -> menus.removeCategoryMenu(op);
                case 40 -> menus.userSubMenu(op);
                case 41 -> menus.userCategorytMenu(op);
                default -> op;
            };
        }
    }
}
