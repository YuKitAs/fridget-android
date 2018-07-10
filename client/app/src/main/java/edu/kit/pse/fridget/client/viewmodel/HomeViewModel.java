package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import java.util.Random;

import edu.kit.pse.fridget.client.activity.CreateTextCoolNoteActivity;
import edu.kit.pse.fridget.client.activity.FullTextCoolNoteActivity;
import edu.kit.pse.fridget.client.datamodel.CoolNote;
import edu.kit.pse.fridget.client.datamodel.FrozenNote;
import edu.kit.pse.fridget.client.datamodel.Member;

public class HomeViewModel extends ViewModel {


    public MutableLiveData<CoolNote[]> liveDatacNList = new MutableLiveData<CoolNote[]>();
    public MutableLiveData<FrozenNote[]> liveDatafNList = new MutableLiveData<FrozenNote[]>();
    public MutableLiveData<Member[]> liveDataMemberList = new MutableLiveData<Member[]>();


    // Die Listen sind nach der Position geordnet... Position = Index+1
    private CoolNote[] cNList = new CoolNote[9];
    private FrozenNote[] fNList = new FrozenNote[3];

    private Member[] memberList = new Member[15];




    // Konstruktor
    public HomeViewModel(){
        updateLists();
        this.liveDatacNList.setValue(this.cNList);
        this.liveDatafNList.setValue(this.fNList);
        this.liveDataMemberList.setValue(this.memberList);
    }

    // alle Listen werden aus dem Server neugeholt
    private void updateLists() {
        this.getcNList();
        this.getfNList();
        this.getMemberList();
    }


    public boolean isEmptyCN(int position) {
        CoolNote temp = this.liveDatacNList.getValue()[position - 1];
        if(temp == null){
            return true;
        }else {
            return false;
        }    }


    public String getCNTitle(int position){
        CoolNote temp = this.liveDatacNList.getValue()[position - 1];
        if(temp == null){
            return "";
        }else {
            return temp.getTitle();
        }
    }

    public String getFNTitle(int position){
        FrozenNote temp = this.liveDatafNList.getValue()[position - 1];
        if(temp == null){
            return "";
        }else {
            return temp.getTitle();
        }
    }

    // get Methode.... hier service aufrufen... alles was hier momentan steht, ist nur zum testen da
    public FrozenNote[] getfNList(){

        return fNList;
    }


    // get Methode.... hier service aufrufen... alles was hier momentan steht, ist nur zum testen da
    public CoolNote[] getcNList(){

        return cNList;
    }

    // get methode.... hier service aufrufen... alles was hier momentan steht, ist nur zum testen da
    public Member[] getMemberList(){
        return memberList;
    }



    public void onPlusButtonClicked(View view){
        int[] emptyPositions = this.getListOfEmptySpaceForCoolNote();
        if (emptyPositions == null) {
            // Fehlermeldung... Man kann keine weiteren CoolNotes mehr einfügen

        } else {
            int position = this.getRandomEmptyPosition(emptyPositions);
            Context context = view.getContext();
            Intent intent = new Intent(context, CreateTextCoolNoteActivity.class);
            intent.putExtra("position", position);
            context.startActivity(intent);
        }

    }

    public void openFullCoolNote(View view) {
        Context context = view.getContext();
        int position = Integer.parseInt(view.getTag().toString());
        String cNid = this.liveDatacNList.getValue()[position-1].getId();
        Intent intent = new Intent(context, FullTextCoolNoteActivity.class);
        intent.putExtra("coolNoteId", cNid);
        context.startActivity(intent);
    }

    public int getMagnetColor(int position){
        if (this.isEmptyCN(position)) {
            return Color.parseColor("#FFFFFF"); // ... alternative?...
        }
        Member tempMember = getMemberbyCoolNotePosition(position-1);
        String magnetColor = tempMember.getMagnetColor();
        return Color.parseColor(magnetColor);
    }

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

    private int getRandomEmptyPosition(int[] arr){
        int[] emptyPositions = arr;
        Random generator = new Random();
        int randomIndex = generator.nextInt(emptyPositions.length);
        return emptyPositions[randomIndex];
    }
}

