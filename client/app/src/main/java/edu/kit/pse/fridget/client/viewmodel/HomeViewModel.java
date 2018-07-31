package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import java.util.LinkedList;
import java.util.Random;

import edu.kit.pse.fridget.client.activity.CreateTextCoolNoteActivity;
import edu.kit.pse.fridget.client.activity.FullTextCoolNoteActivity;
import edu.kit.pse.fridget.client.activity.FullTextFrozenNoteActivity;
import edu.kit.pse.fridget.client.activity.MenuDrawerActivity;
import edu.kit.pse.fridget.client.datamodel.CoolNote;
import edu.kit.pse.fridget.client.datamodel.FrozenNote;
import edu.kit.pse.fridget.client.datamodel.Member;

/**
 * Diese Klasse stellt alle benötigten Informationen zur Verfügung, die die HomeActivity benötigt
 */
public class HomeViewModel extends ViewModel {


    public MutableLiveData<CoolNote[]> liveDataCNList = new MutableLiveData<CoolNote[]>();
    public MutableLiveData<Boolean[]> liveDataVisibilityList = new MutableLiveData<Boolean[]>();

    public MutableLiveData<FrozenNote[]> liveDataFNList = new MutableLiveData<FrozenNote[]>();

    public MutableLiveData<Integer[]> liveDataMagnetColorList = new MutableLiveData<Integer[]>();


    // Die Listen sind nach der Position geordnet... Position = Index+1
    private CoolNote[] cNList = new CoolNote[9];
    private FrozenNote[] fNList = new FrozenNote[3];
    private Boolean[] visibilityList = new Boolean[9];

    private Member[] memberList = new Member[15];
    private Integer[] magnetColorList = new Integer[9];


    /**
     * Konstruktor
     */
    public HomeViewModel() {
        this.fakeCN(); // wird gelöscht
        this.fakeFN(); // wird gelöscht
        this.fakeMembers(); // wird gelöscht
        this.setLiveDataVisibilityList(this.cNList);
        this.setLiveDataMagnetColorList(this.getMemberList());
        updateLists();
    }

    /**
     * alle Listen werden aus dem Server neugeholt
     */
    public void updateLists() {

        this.liveDataCNList.setValue(this.getcNList());
        this.liveDataVisibilityList.setValue(this.visibilityList);
        this.liveDataFNList.setValue(this.getfNList());
        this.liveDataMagnetColorList.setValue(this.magnetColorList);

    }

    /**
     * get Methode für die Liste der FrozenNotes
     * hier verbinden mit dem Server
     *
     * @return
     */
    public FrozenNote[] getfNList() {

        return fNList;
    }


    /**
     * get Methode für die Liste der CoolNotes
     * hier verbinden mit dem Server
     *
     * @return
     */
    public CoolNote[] getcNList() {


        // visibiltyList updaten
        this.setLiveDataVisibilityList(this.cNList);
        this.setLiveDataMagnetColorList(this.getMemberList());
        return cNList;
    }

    /**
     * get Methode für die Liste der Mitglieder
     * hier verbinden mit dem Server
     *
     * @return
     */
    public Member[] getMemberList() {

        return memberList;
    }


    public void setLiveDataMagnetColorList(Member[] memberlist) {
        CoolNote[] cList = this.cNList;

        int i = 0;
        for (CoolNote cn : cList) {
            if (cn != null) {
                this.magnetColorList[i] = getMemberColorbyUserId(cn.getCreatorMembershipId(), memberlist);
            } else {
                this.magnetColorList[i] = Color.parseColor("#FFFFFF"); // wird invisible gesetzt... also egal, welche Farbe
            }
            i++;
        }
    }

    private int getMemberColorbyUserId(String id, Member[] memberlist) {
        Member[] mlist = memberlist;

        for (Member m : mlist) {
            if (m.getUserId().equals(id)) {
                return Color.parseColor(m.getMagnetColor());
            }
        }
        return Color.parseColor("#FFFFFF"); // sollte nie erreicht werden... sonst heißt es, dass es den Member nicht gibt
    }


    public void setLiveDataVisibilityList(CoolNote[] list) {
        int i = 0;
        for (CoolNote cn : list) {
            if (cn != null) {
                this.visibilityList[i] = true;
            } else {
                this.visibilityList[i] = false;
            }
            i++;
        }

        this.liveDataVisibilityList.setValue(this.visibilityList);
    }

    /**
     * Methode führt Activitywechsel durch, wenn bereits neun CoolNotes existieren, erscheint eine Meldung
     *
     * @param view
     */
    public void onPlusButtonClicked(View view) {
        LinkedList<Integer> emptyPositions = this.getListOfEmptySpaceForCoolNote();
        if (emptyPositions == null) {
            // Fehlermeldung... Man kann keine weiteren CoolNotes mehr einfügen


        } else {
            int position = this.getRandomEmptyPosition();
            Context context = view.getContext();
            Intent intent = new Intent(context, CreateTextCoolNoteActivity.class);
            intent.putExtra("position", position);
            context.startActivity(intent);

            //testen
            CoolNote cN3 = new CoolNote("3", "lelele", "testtest",
                    "2", 3, 1, null, null);
            this.cNList[position] = cN3;

            this.updateLists();
        }

    }

    public void onMenuButtonClicked(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, MenuDrawerActivity.class);
        context.startActivity(intent);
        this.updateLists();
    }

    public void onRefreshButtonClicked(View view) {
        // testen
       /* LinkedList<Integer> emptyPositions = this.getListOfEmptySpaceForCoolNote();
        int position = this.getRandomEmptyPosition(emptyPositions);
        CoolNote cN4 = new CoolNote("4","dsfgdfg","testtest, sdfgsdfg",
                "0","2","10072018",2);
        this.cNList[position]=cN4;
        updateLists(); */


        updateLists();

    }


    /**
     * Methode führt Activitywechsel durch, wenn auf die CoolNotes gedrückt wird
     *
     * @param view
     */
    public void openFullCoolNote(View view) {
        Context context = view.getContext();
        int position = Integer.parseInt(view.getTag().toString());
        String cNid = this.liveDataCNList.getValue()[position - 1].getId();
        Intent intent = new Intent(context, FullTextCoolNoteActivity.class);
        intent.putExtra("coolNoteId", cNid);
        context.startActivity(intent);

        this.updateLists();
    }

    /**
     * Methode führt Activitywechsel durch, wenn auf die FrozenNotes gedrückt wird
     *
     * @param view
     */
    public void openFullFrozenNote(View view) {
        Context context = view.getContext();
        int position = Integer.parseInt(view.getTag().toString());
        String fNid = this.liveDataFNList.getValue()[position - 1].getId();
        Intent intent = new Intent(context, FullTextFrozenNoteActivity.class);
        intent.putExtra("frozenNoteId", fNid);
        context.startActivity(intent);

        this.updateLists();
    }


    /**
     * Diese Methode sucht alle leeren Indizes in der Liste von CoolNotes,
     * Dabei wird immer die Liste vom Server benutzt
     *
     * @return IntArray von allen Indizes, die leer sind
     */
    private LinkedList<Integer> getListOfEmptySpaceForCoolNote() {
        CoolNote[] cnList = this.getcNList();
        LinkedList<Integer> arr = new LinkedList<Integer>();
        int i = 0;
        for (int j = 0; j < 9; j++) {
            CoolNote cn = cnList[j];
            if (cn == null) {
                arr.add(j);
                i++;
            }
        }
        if (i == 0) {
            arr = null;
        }
        return arr;
    }


    /**
     * berechnet eine zufällige leere Position
     * @return
     */
    private int getRandomEmptyPosition() {
        LinkedList<Integer> emptyPositions = getListOfEmptySpaceForCoolNote();
        Random generator = new Random();
        int randomIndex = 0;
        while (randomIndex == 0) {
            randomIndex = generator.nextInt(emptyPositions.size());
        }
        return emptyPositions.get(randomIndex);
    }

    /**
     * wird gelöscht
     */
    private void fakeCN() {

        CoolNote cN1 = new CoolNote("1", "Boo", "testtest",
                "0", 1, 1, null, null);
        CoolNote cN2 = new CoolNote("2", "lalala", "testtest",
                "1", 2, 1, null, null);
        CoolNote cN3 = new CoolNote("3", "lelele", "testtest",
                "2", 3, 1, null, null);
        CoolNote cN4 = new CoolNote("4", "lululu", "testtest",
                "0", 4, 1, null, null);
        CoolNote cN5 = new CoolNote("5", "lololol", "testtest",
                "2", 5, 1, null, null);
        CoolNote cN6 = new CoolNote("6", "nya", "testtestn",
                "2", 6, 1, null, null);
        CoolNote cN7 = new CoolNote("7", "meow", "testtest",
                "1", 7, 1, null, null);
        CoolNote cN8 = new CoolNote("8", "yaay", "testtest",
                "1", 8, 1, null, null);
        CoolNote cN9 = new CoolNote("9", "brr", "testtest",
                "0", 9, 1, null, null);

        this.cNList[0] = cN1;
        this.cNList[1] = cN2;
        this.cNList[2] = null;
        this.cNList[3] = cN4;
        this.cNList[4] = cN5;
        this.cNList[5] = cN6;
        this.cNList[6] = cN7;
        this.cNList[7] = cN8;
        this.cNList[8] = null;

    }


    /**
     * wird gelöscht
     */
    private void fakeFN() {
        FrozenNote fN1 = new FrozenNote("0", "Notfallkontakte", "brr brr", "0", 0);
        FrozenNote fN2 = new FrozenNote("1", "Einkaufsliste", "brr brr", "0", 1);
        FrozenNote fN3 = new FrozenNote("2", "nomnom", "brr brr", "0", 2);

        this.fNList[0] = fN1;
        this.fNList[1] = fN2;
        this.fNList[2] = fN3;

    }

    /**
     * wird gelöscht
     */
    private void fakeMembers() {
        Member m1 = new Member("0", "0", "0", "#4B088A");
        Member m2 = new Member("1", "1", "0", "#ff00ab");
        Member m3 = new Member("2", "2", "0", "#2f9624");
        this.memberList[0] = m1;
        this.memberList[1] = m2;
        this.memberList[2] = m3;

    }


}
