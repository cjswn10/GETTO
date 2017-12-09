package controller;

import controller.action.Action;
import controller.action.BmiSearchAction;
import controller.action.DateSearchAction;
import controller.action.DeleteAction;
import controller.action.DietListInsertAction;
import controller.action.DiseaseSearchAction;
import controller.action.ExerListInsertAction;
import controller.action.ExerSearchAction;
import controller.action.FindExeAction;
import controller.action.FirstDateSearchAction;
import controller.action.FoodInsertAction;
import controller.action.FoodSearchAction;
import controller.action.HospSearchByArea;
import controller.action.HospitalSearchAction;
import controller.action.IllegalCommandException;
import controller.action.InsertAction;
import controller.action.ListAction;
import controller.action.LoginAction;
import controller.action.LogoutAction;
import controller.action.RecFoodAction;
import controller.action.GoodFoodAction;
import controller.action.UpdateAction;
import controller.action.ViewAction;
import controller.action.mainSearchAction;
import controller.action.searchUserListAction;
import controller.action.userListAction;


public class UserCommandFactory {

	private UserCommandFactory() {}

	public static UserCommandFactory getInstance() {
		return new UserCommandFactory();
	}

	/*
	 * 수행해야할 명령어에 해당하는 Action객체를 생성.
	 */
	public Action getAction(String command) throws IllegalCommandException {
		Action action = null;

		if (command.equals("list")) {
			action = new ListAction();
		} else if (command.equals("view")) {
			action = new ViewAction();
		} else if (command.equals("insert")) {
			action = new InsertAction();
		} else if (command.equals("update")) {
			action = new UpdateAction();
		} else if (command.equals("delete")) {
			action = new DeleteAction();
		} else if (command.equals("login")) {
			action = new LoginAction();
		} else if (command.equals("logout")) {
			action = new LogoutAction();
		} else if (command.equals("foodSearch")){
			action = new FoodSearchAction();
		} else if (command.equals("exerSearch")){
			action = new ExerSearchAction();
		} else if (command.equals("dateSearch")){
			action = new DateSearchAction();
		} else if (command.equals("firstdateSearch")){
			action = new FirstDateSearchAction();
		} else if (command.equals("dietlistinsert")) {
			action = new DietListInsertAction();
		} else if (command.equals("exerlistinsert")) {
			action = new ExerListInsertAction();
		} else if (command.equals("bmiSearch")) {
			action = new BmiSearchAction();
		} else if (command.equals("foodSearch")){
			action = new FoodSearchAction();
		} else if (command.equals("recFood")) {
			action = new RecFoodAction();
		} else if (command.equals("goodFood")) {
			action = new GoodFoodAction();
		} else if (command.equals("diseaseSearch")) {
			action = new DiseaseSearchAction();
		} else if (command.equals("findExe")) {
			action = new FindExeAction();
		} else if (command.equals("statsCheck")) {
			action = new userListAction();
		} else if (command.equals("mainSearch")){
			action = new mainSearchAction();
		} else if (command.equals("hospitalSearch")) {
			action = new HospitalSearchAction();
		}  else if (command.equals("hospSearchByArea")) {
			action = new HospSearchByArea();
		} else if (command.equals("insertFood")){
			action = new FoodInsertAction();
		}else if (command.equals("searchUserList")){
			action = new searchUserListAction();
		}
		
		
		else {
			throw new IllegalCommandException("잘못된 실행명령입니다. 다른 명령을 실행해 주십시요");
		}

		return action;
	}
}
