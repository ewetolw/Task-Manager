package com.pd.eweltol.taskmanager.model.types;

public enum ProblemStatus {

    NEW(1),ATTACHED(2),FINISHED(3);

    private int nr;

    ProblemStatus(int nr){
        this.nr=nr;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }
}
