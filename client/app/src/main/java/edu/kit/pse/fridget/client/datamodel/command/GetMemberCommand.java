package edu.kit.pse.fridget.client.datamodel.command;

public class GetMemberCommand {




    private final String membershipId;
    private final String  magnetColor;
    private final String googleName;

    public GetMemberCommand(String membershipId, String magnetColor, String googleName){
    this.googleName =googleName;
    this.magnetColor =magnetColor;
    this.membershipId =membershipId;
    }

    public String getMemberId() {
        return membershipId;}

    public String getMagnetColor() {
        return magnetColor;
    }

    public String getGoogleName() {
        return googleName;
    }
}
