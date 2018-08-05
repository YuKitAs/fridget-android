package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import edu.kit.pse.fridget.client.activity.CreateTextCoolNoteActivity;
import edu.kit.pse.fridget.client.activity.FullTextCoolNoteActivity;
import edu.kit.pse.fridget.client.activity.FullTextFrozenNoteActivity;
import edu.kit.pse.fridget.client.activity.MenuDrawerActivity;
import edu.kit.pse.fridget.client.datamodel.CoolNote;
import edu.kit.pse.fridget.client.datamodel.FrozenNote;
import edu.kit.pse.fridget.client.datamodel.Member;
import edu.kit.pse.fridget.client.service.CoolNoteService;
import edu.kit.pse.fridget.client.service.FrozenNoteService;
import edu.kit.pse.fridget.client.service.MembershipService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private String flatshareId;

    /**
     * Konstruktor
     */
    public HomeViewModel() {
        this.fakeCN(); // wird gelöscht
        this.fakeFN(); // wird gelöscht
        this.fakeMembers(); // wird gelöscht

        this.setFlatsharIDFromSP();
       // this.setLiveDataVisibilityList(this.cNList);
       // this.setLiveDataMagnetColorList(this.getMemberList());
        updateLists();
    }

    private void setFlatsharIDFromSP() {
        String id = null;

        this.flatshareId = id;
    }

    private String getFlatshareId() {
        return this.flatshareId;
    }

    /**
     * alle Listen werden aus dem Server neugeholt
     */
    public void updateLists() {
        this.liveDataCNList.setValue(this.getcNList());
        this.liveDataFNList.setValue(this.getfNList());
    }

    /**
     * get Methode für die Liste der FrozenNotes
     * hier verbinden mit dem Server
     *
     * @return
     */
    public FrozenNote[] getfNList() {
        RetrofitClientInstance.getRetrofitInstance().create(FrozenNoteService.class).getAllFrozenNote(flatshareId).enqueue(new Callback<List<FrozenNote>>() {
            @Override
            public void onResponse(Call<List<FrozenNote>> call, Response<List<FrozenNote>> response) {
                //fNList darf nie null sein, da immer FrozenNotes existieren
                fNList = null;

                List<FrozenNote> frozenNotes = response.body();
                if (frozenNotes != null) {
                    // Liste in ein nach der Position geordneten Array umwandeln
                    for (FrozenNote fn : frozenNotes) {
                        fNList[fn.getPosition() - 1] = fn;
                    }
                } else {
                    Log.e("getfNList1", "There are no Frozen Note.");

                }
            }

            @Override
            public void onFailure(Call<List<FrozenNote>> call, Throwable t) {

            }
        });
        return fNList;
    }


    /**
     * get Methode für die Liste der CoolNotes
     * hier verbinden mit dem Server
     *
     * @return
     */
    public CoolNote[] getcNList() {


        RetrofitClientInstance.getRetrofitInstance().create(CoolNoteService.class).getAllCoolNotes(flatshareId).enqueue(new Callback<List<CoolNote>>() {
            @Override
            public void onResponse(Call<List<CoolNote>> call, Response<List<CoolNote>> response) {
                cNList = null;
                List<CoolNote> coolNotes = response.body();

                if (coolNotes != null) {
                    // Liste in ein nach der Position geordneten Array umwandeln
                    for (CoolNote cn : coolNotes) {
                        cNList[coolNotes.get(0).getPosition() - 1] = cn;
                    }
                } else {
                    Log.e("getcNList1", "There are no Cool Note.");

                }
            }

            @Override
            public void onFailure(Call<List<CoolNote>> call, Throwable t) {
                Log.e("getcNList2", "Get CoolNoteList failed.");

            }
        });

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

        RetrofitClientInstance.getRetrofitInstance().create(MembershipService.class).getMemberList(flatshareId).enqueue(new Callback<List<Member>>() {
            @Override
            public void onResponse(Call<List<Member>> call, Response<List<Member>> response) {
                memberList = null;
                List<Member> members = response.body();

                if (members != null) {
                    for (int m = 0; m < 15; m++) {
                        memberList[m] = members.get(m);
                    }
                } else {
                    Log.e("getmemberList1", "There are no Members.");

                }
            }

            @Override
            public void onFailure(Call<List<Member>> call, Throwable t) {

            }
        });

        return memberList;
    }


    public void setLiveDataMagnetColorList(Member[] memberlist) {
        CoolNote[] cList = this.cNList;

        int i = 0;
        if (cNList != null) {
            for (CoolNote cn : cList) {
                if (cn != null) {
                    this.magnetColorList[i] = getMemberColorbyUserId(cn.getCreatorMembershipId(), memberlist);
                } else {
                    this.magnetColorList[i] = Color.parseColor("#FFFFFF"); // wird invisible gesetzt... also egal, welche Farbe
                }
                i++;
            }
        } else {
            for (int n = 0; n < 9; n++) {
                this.magnetColorList[n] = Color.parseColor("#FFFFFF");
            }
        }

        this.liveDataMagnetColorList.setValue(this.magnetColorList);
    }

    private int getMemberColorbyUserId(String id, Member[] memberlist) {
        Member[] mlist = memberlist;

        if (mlist == null) {
            Log.e("member","The member is not found");
            return Color.parseColor("#FFFFFF"); // sollte nie erreicht werden... sonst heißt es, dass es den Member nicht gibt
        }

        for (Member m : mlist) {
            if (m.getUserId().equals(id)) {
                return Color.parseColor(m.getMagnetColor());
            }
        }
        return Color.parseColor("#FFFFFF"); // sollte nie erreicht werden... sonst heißt es, dass es den Member nicht gibt

    }


    public void setLiveDataVisibilityList(CoolNote[] list) {
        int i = 0;
        if (list != null) {
            for (CoolNote cn : list) {
                if (cn != null) {
                    this.visibilityList[i] = true;
                } else {
                    this.visibilityList[i] = false;
                }
                i++;
            }
        } else {
            this.visibilityList = null;
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
            Toast.makeText(view.getContext(), "You can't add more Cool Note. Please delete some CoolNotes.",Toast.LENGTH_SHORT).show();

        } else {
            int position = this.getRandomEmptyPosition();
            Context context = view.getContext();
            Intent intent = new Intent(context, CreateTextCoolNoteActivity.class);
            intent.putExtra("position", position);
            context.startActivity(intent);
        }

    }

    public void onMenuButtonClicked(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, MenuDrawerActivity.class);
        context.startActivity(intent);
        this.updateLists();
    }

    public void onRefreshButtonClicked(View view) {
        /*//testen
        int position = this.getRandomEmptyPosition();

        CoolNote cN3 = new CoolNote("3", "piep", "testtest",
                "2", 3, 1, null, null);
        this.cNList[position] = cN3; */

        updateLists();

    }


    /**
     * Methode führt Activitywechsel durch, wenn auf die CoolNotes gedrückt wird
     *
     * @param view
     */
    public void openFullCoolNote(View view) {
        this.updateLists();
        int position = Integer.parseInt(view.getTag().toString());
        if (liveDataCNList.getValue() != null ) {
            if (this.liveDataCNList.getValue()[position - 1] != null) {
                Context context = view.getContext();
                String cNid = this.liveDataCNList.getValue()[position - 1].getId();
                Intent intent = new Intent(context, FullTextCoolNoteActivity.class);
                intent.putExtra("coolNoteId", cNid);
                context.startActivity(intent);
            } else {
                Toast.makeText(view.getContext(),"This Cool Note was deleted.",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(view.getContext(),"This Cool Note was deleted.",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Methode führt Activitywechsel durch, wenn auf die FrozenNotes gedrückt wird
     *
     * @param view
     */
    public void openFullFrozenNote(View view) {
        this.updateLists();
        Context context = view.getContext();
        int position = Integer.parseInt(view.getTag().toString());

        if (liveDataCNList.getValue() != null) {
            if ( this.liveDataFNList.getValue()[position - 1] != null) {
                String fNid = this.liveDataFNList.getValue()[position - 1].getId();
                Intent intent = new Intent(context, FullTextFrozenNoteActivity.class);
                intent.putExtra("frozenNoteId", fNid);
                context.startActivity(intent);
            }
        } else {
            // sollte nie erreicht werden, da es immer 3 Frozen Notes gibt.
            Log.e("frozenNote", "Get FrozenNote failed.");
        }
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
        this.cNList[2] = cN3;
        this.cNList[3] = cN4;
        this.cNList[4] = cN5;
        this.cNList[5] = cN6;
        this.cNList[6] = cN7;
        this.cNList[7] = cN8;
        this.cNList[8] = cN9;

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
