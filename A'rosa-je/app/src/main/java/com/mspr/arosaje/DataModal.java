package com.mspr.arosaje;


public class DataModal {

    // string variables for our name and job
    private String get_post;
    private String get_code_post;
    private String get_ville;
    private String get_email;
    private String get_mdp;
    private String get_conf_mdp;

    public DataModal(String get_post, String get_code_post, String get_ville, String get_email, String get_mdp, String get_conf_mdp) {
        this.get_post = get_post;
        this.get_code_post = get_code_post;
        this.get_ville = get_ville;
        this.get_email = get_email;
        this.get_mdp = get_mdp;
        this.get_conf_mdp = get_conf_mdp;
    }

    /*public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }*/

}
