package edu.kit.pse.fridget.client.datamodel.representation;

public class UserMembershipRepresentation {
    private final String membershipId;
    private final String magnetColor;
    private final String googleName;

    public UserMembershipRepresentation(String membershipId, String magnetColor, String googleName) {
        this.googleName = googleName;
        this.magnetColor = magnetColor;
        this.membershipId = membershipId;
    }

    public String getMemberId() {
        return membershipId;
    }

    public String getMagnetColor() {
        return magnetColor;
    }

    public String getGoogleName() {
        return googleName;
    }
}
