package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import java.util.Random;

import edu.kit.pse.fridget.client.activity.CreateTextCoolNoteActivity;
import edu.kit.pse.fridget.client.activity.FullTextCoolNoteActivity;
import edu.kit.pse.fridget.client.activity.FullTextFrozenNoteActivity;
import edu.kit.pse.fridget.client.activity.MenuMainActivity;
import edu.kit.pse.fridget.client.datamodel.CoolNote;
import edu.kit.pse.fridget.client.datamodel.FrozenNote;
import edu.kit.pse.fridget.client.datamodel.Member;

/**
 * Diese Klasse stellt alle benötigten Informationen zur Verfügung, die HomeActivity benötigt
 */
public class HomeViewModel extends ViewModel {


    public MutableLiveData<CoolNote[]> liveDataCNList = new MutableLiveData<CoolNote[]>();
    public MutableLiveData<FrozenNote[]> liveDataFNList = new MutableLiveData<FrozenNote[]>();
    public MutableLiveData<Member[]> liveDataMemberList = new MutableLiveData<Member[]>();


    // Die Listen sind nach der Position geordnet... Position = Index+1
    private CoolNote[] cNList = new CoolNote[9];
    private FrozenNote[] fNList = new FrozenNote[3];

    private Member[] memberList = new Member[15];



    /**
     * Konstruktor
     */
    public HomeViewModel(){
        updateLists();
    }

    /**
     *  alle Listen werden aus dem Server neugeholt
     */
    public void updateLists() {
        this.getcNList();
        this.getfNList();
        this.getMemberList();
        this.liveDataCNList.setValue(this.cNList);
        this.liveDataFNList.setValue(this.fNList);
        this.liveDataMemberList.setValue(this.memberList);
    }

    /**
     * überprüft, ob eine CoolNote an der übergebenen Position existiert
     * @param position
     * @return
     */
    public boolean isEmptyCN(int position) {
        CoolNote temp = this.liveDataCNList.getValue()[position - 1];
        if(temp == null){
            return true;
        }else {
            return false;
        }    }

    /**
     * gibt die Überschrift der CoolNote an der übergebenen Position wieder
     * @param position
     * @return
     */
    public String getCNTitle(int position){
        CoolNote temp = this.liveDataCNList.getValue()[position - 1];
        if(temp == null){
            return "";
        }else {
            return temp.getTitle();
        }
    }

    /**
     * gibt die Überschrift der FrozenNote an der übergebenen Position wieder
     * @param position
     * @return
     */
    public String getFNTitle(int position){
        FrozenNote temp = this.liveDataFNList.getValue()[position - 1];
        if(temp == null){
            return "";
        }else {
            return temp.getTitle();
        }
    }

    /**
     * get Methode für die Liste der FrozenNotes, alles was hier steht ist momentan nur zum testen da,
     * hier verbinden mit dem Server
     * @return
     */
    public FrozenNote[] getfNList(){
        FrozenNote fN1 = new FrozenNote("0","Notfallkontakte", "brr brr", "0", 0);
        FrozenNote fN2 = new FrozenNote("1","Einkaufsliste", "brr brr", "0", 1);
        FrozenNote fN3 = new FrozenNote("2","nomnom", "brr brr", "0", 2);

        this.fNList[0] = fN1;
        this.fNList[1] = fN2;
        this.fNList[2] = fN3;

        return fNList;
    }


    /**
     * get Methode für die Liste der CoolNotes, alles was hier steht ist momentan nur zum testen da,
     * hier verbinden mit dem Server
     * @return
     */
    public CoolNote[] getcNList(){

        CoolNote cN1 = new CoolNote("1","Boo","testtest",
                "0","0","10072018",1);
        CoolNote cN2 = new CoolNote("2","lalala","testtest",
                "0","1","10072018",2);
        CoolNote cN3 = new CoolNote("3","lelele","testtest",
                "0","1","10072018",2);
        CoolNote cN4 = new CoolNote("4","lululu","testtest",
                "0","2","10072018",2);
        CoolNote cN5 = new CoolNote("5","lololol","testtest",
                "0","0","10072018",2);
        CoolNote cN6 = new CoolNote("6","nya","testtestn",
                "0","2","10072018",2);
        CoolNote cN7 = new CoolNote("7","meow","testtest",
                "0","2","10072018",2);
        CoolNote cN8 = new CoolNote("8","yaay","testtest",
                "0","0","10072018",2);
        CoolNote cN9 = new CoolNote("9","brr","testtest",
                "0","0","10072018",2);

        this.cNList[0] = cN1;
        this.cNList[1] = cN2;
        this.cNList[2] = cN3;
        this.cNList[3] = cN4;
        this.cNList[4] = cN5;
        this.cNList[5] = cN6;
        this.cNList[6] = cN7;
        this.cNList[7] = cN8;
        this.cNList[8] = cN9;

        return cNList;
    }

    /**
     * get Methode für die Liste der Mitglieder, alles was hier steht ist momentan nur zum testen da,
     * hier verbinden mit dem Server
     * @return
     */
    public Member[] getMemberList(){
        Member m1 = new Member("0","0","0","#4B088A");
        Member m2 = new Member("1","1","0","#ff00ab");
        Member m3 = new Member("2","2","0","#2f9624");
        this.memberList[0] = m1;
        this.memberList[1] = m2;
        this.memberList[2] = m3;

        return memberList;
    }


    /**
     * Methode führt Activitywechsel durch, wenn bereits neun CoolNotes existieren, erscheint eine Meldung
     * @param view
     */
    public void onPlusButtonClicked(View view){
        int[] emptyPositions = this.getListOfEmptySpaceForCoolNote();
        if (emptyPositions == null) {
            // Fehlermeldung... Man kann keine weiteren CoolNotes mehr einfügen


        } else {
          /* // testen
            int position = this.getRandomEmptyPosition(emptyPositions);
            CoolNote cN4 = new CoolNote("4","dsfgdfg","testtest, sdfgsdfg",
                    "0","2","10072018",2);
            this.cNList[position]=cN4;

            updateLists(); */

            int position = this.getRandomEmptyPosition(emptyPositions);
            Context context = view.getContext();
            Intent intent = new Intent(context, CreateTextCoolNoteActivity.class);
            intent.putExtra("position", position);
            context.startActivity(intent);
            this.updateLists();
        }

    }

    public void onMenuButtonClicked(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, MenuMainActivity.class);
        context.startActivity(intent);
        this.updateLists();
    }

    /**
     * Methode führt Activitywechsel durch, wenn auf die CoolNotes gedrückt wird
     * @param view
     */
    public void openFullCoolNote(View view) {
        Context context = view.getContext();
        int position = Integer.parseInt(view.getTag().toString());
        String cNid = this.liveDataCNList.getValue()[position-1].getId();
        Intent intent = new Intent(context, FullTextCoolNoteActivity.class);
        intent.putExtra("coolNoteId", cNid);
        context.startActivity(intent);

        this.updateLists();
    }

    /**
     *  Methode führt Activitywechsel durch, wenn auf die FrozenNotes gedrückt wird
     * @param view
     */
    public void openFullFrozenNote(View view) {
        Context context = view.getContext();
        int position = Integer.parseInt(view.getTag().toString());
        String fNid = this.liveDataFNList.getValue()[position-1].getId();
        Intent intent = new Intent(context, FullTextFrozenNoteActivity.class);
        intent.putExtra("frozenNoteId", fNid);
        context.startActivity(intent);

        this.updateLists();
    }

    /**
     * @param position
     * @return
     */
    public int getMagnetColor(int position){
        if (this.isEmptyCN(position)) {
            return Color.parseColor("#FFFFFF"); // ... alternative?...
        }
        Member tempMember = getMemberbyCoolNotePosition(position-1);
        String magnetColor = tempMember.getMagnetColor();
        return Color.parseColor(magnetColor);
    }

    /**
     * findet das Mitglied heraus, der die CoolNote an der übergebenen Position erstellt hat
     * @param index Position der CoolNote
     * @return Mitglied
     */
    private Member getMemberbyCoolNotePosition(int index) {
        CoolNote tempcN = this.cNList[index];
        String userId = tempcN.getCreatorUserId();
        Member member = null;

        for (Member tempMember : this.memberList){
            if (tempMember != null && tempMember.getUserId().equals(userId)) {
                return tempMember;
            }
        }
        return member; // sollte nie erreicht werden
    }


    /**
     * Diese Methode sucht alle leeren Indizes in der Liste von CoolNotes
     * @return IntArray von allen Indizes, die leer sind
     */
    private int[] getListOfEmptySpaceForCoolNote(){
        int[] arr = new int[9];
        int i = 0;
        for(int j = 0; j < 9; j++) {
            CoolNote cn = this.cNList[j];
            if ( cn == null){
                arr[i]=j;
                i++;
            }
        }
        if (i == 0){
            arr= null;
        }
        return arr;
    }


    /**
     * berechnet eine zufällige leere Position
     * @param arr Liste von allen Indizes, die in der CoolNote Liste leer sind
     * @return eine zufällige Position
     */
    private int getRandomEmptyPosition(int[] arr){
        int[] emptyPositions = arr;
        Random generator = new Random();
        int randomIndex = generator.nextInt(emptyPositions.length);
        return emptyPositions[randomIndex];
    }
}

