package com.pd.eweltol.taskmanager.model.types;

public enum TaskStatus {
    NEW(1),ATTACHED(2),INPROGRESS(3),SUSPENDED(3),CANCELED(4),FINISHED(4);
    private int nr;

    TaskStatus(int i){
        nr=i;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }
}
