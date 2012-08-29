package controllers;

import play.mvc.Controller;
import controllers.response.Ok;
import controllers.response.TodoMal;
import flexjson.JSONSerializer;

public class BaseService extends Controller {

	protected static void jsonOk(Object obj) {
		renderJSON(new JSONSerializer().serialize(new Ok(obj)));
	}
	
	protected static void jsonError(Object obj) {
		renderJSON(new JSONSerializer().serialize(new TodoMal(obj)));
	}

}
