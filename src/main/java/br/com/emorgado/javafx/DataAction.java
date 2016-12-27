package br.com.emorgado.javafx;

public class DataAction<T> {

	private T data;
	private ActionType actionType = ActionType.NONE;

	public DataAction() {
		super();
	}

	public DataAction(T data) {
		super();
		this.data = data;
	}

	public DataAction(T data, ActionType actionType) {
		super();
		this.data = data;
		this.actionType = actionType;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setSave() {
		this.actionType = ActionType.SAVE;
	}
	
	public void setSave(T data) {
	    this.data = data;
        this.actionType = ActionType.SAVE;
    }

	public boolean isSave() {
		return actionType.equals(ActionType.SAVE);
	}

	public void setUpdate() {
		this.actionType = ActionType.UPDATE;
	}

	public void setUpdate(T data) {
        this.data = data;
        this.actionType = ActionType.UPDATE;
    }
	
	public boolean isUpdate() {
		return actionType.equals(ActionType.UPDATE);
	}

	public void setDelete() {
		this.actionType = ActionType.DELETE;
	}
	
	public void setDelete(T data) {
        this.data = data;
        this.actionType = ActionType.DELETE;
    }

	public boolean isDelete() {
		return actionType.equals(ActionType.DELETE);
	}

	public void setNone() {	    
		this.actionType = ActionType.NONE;
	}

	public boolean isNone() {
		return actionType.equals(ActionType.NONE);
	}
	
	public void setConfirm(T data) {
        this.data = data;
	    this.actionType = ActionType.CONFIRM;
	}
	
	public void setConfirm(){
        this.actionType = ActionType.CONFIRM;
    }
	
	public boolean isConfirm(){
	    return this.actionType.equals(ActionType.CONFIRM);
	}
	
	public void setCancel(){
        this.actionType = ActionType.CANCEL;
    }
	
	public void setCancel(T data) {
        this.data = data;
        this.actionType = ActionType.CANCEL;
    }
    
    public boolean isCancel(){
        return this.actionType.equals(ActionType.CANCEL);
    }

	public enum ActionType {

		SAVE, UPDATE, DELETE, NONE, CONFIRM, CANCEL;

	}

	@Override
	public String toString() {
		return "DataAction [actionType=" + actionType + ", data=" + data + "]";
	}

}
