package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import edu.kit.pse.fridget.client.activity.CreateTextCoolNoteActivity;
import edu.kit.pse.fridget.client.activity.FullTextCoolNoteActivity;
import edu.kit.pse.fridget.client.activity.FullTextFrozenNoteActivity;
import edu.kit.pse.fridget.client.datamodel.CoolNote;
import edu.kit.pse.fridget.client.datamodel.FrozenNote;
import edu.kit.pse.fridget.client.datamodel.representation.UserMembershipRepresentation;
import edu.kit.pse.fridget.client.service.CoolNoteService;
import edu.kit.pse.fridget.client.service.FrozenNoteService;
import edu.kit.pse.fridget.client.service.MembershipService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import edu.kit.pse.fridget.client.utility.MagnetColorUtilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Diese Klasse stellt alle benötigten Informationen zur Verfügung, die die HomeActivity benötigt
 */
public class HomeViewModel extends ViewModel {
    private static final int NUM_OF_COOL_NOTES = 9;
    private static final int NUM_OF_FROZEN_NOTES = 3;
    private static final int NUM_OF_MEMBERS = 15;

    public MutableLiveData<CoolNote[]> liveDataCNList = new MutableLiveData<CoolNote[]>();
    public MutableLiveData<Boolean[]> liveDataVisibilityList = new MutableLiveData<Boolean[]>();
    public MutableLiveData<Integer[]> liveDataMagnetColorList = new MutableLiveData<Integer[]>();

    public MutableLiveData<FrozenNote[]> liveDataFNList = new MutableLiveData<FrozenNote[]>();

    // Die Listen sind nach der Position geordnet
    private CoolNote[] cNList = new CoolNote[NUM_OF_COOL_NOTES];
    private Boolean[] visibilityList = new Boolean[NUM_OF_COOL_NOTES];
    private Integer[] magnetColorList = new Integer[NUM_OF_COOL_NOTES];

    private FrozenNote[] fNList = new FrozenNote[NUM_OF_FROZEN_NOTES];

    private UserMembershipRepresentation[] memberList = new UserMembershipRepresentation[NUM_OF_MEMBERS];

    private String flatshareId;

    /**
     * Konstruktor
     */
    public HomeViewModel() {
    }

    /**
     * wird in der HomeActivity aufgerufen, durch Shared Preference wird dort die FlatshareId
     * hierher übergeben
     */
    public void setFlatshareId(String id) {
        this.flatshareId = id;
    }

    /**
     * load everything from server
     */
    public void fetchData() {
        fetchMemberList();
        fetchFrozenNoteList();
        fetchCoolNoteList();
    }

    /**
     * update view with data loaded from server
     */
    public void updateView() {
        this.liveDataFNList.setValue(fNList);
        this.liveDataCNList.setValue(cNList);

        this.setLiveDataVisibilityList(this.cNList);
        this.setLiveDataMagnetColorList(this.memberList);
    }

    /**
     * get Methode für die Liste der FrozenNotes
     * hier verbinden mit dem Server
     */
    private void fetchFrozenNoteList() {
        RetrofitClientInstance.getRetrofitInstance().create(FrozenNoteService.class).getAllFrozenNote(flatshareId).enqueue(new Callback<List<FrozenNote>>() {
            @Override
            public void onResponse(Call<List<FrozenNote>> call, Response<List<FrozenNote>> response) {
                //fNList darf nie null sein, da immer FrozenNotes existieren
                fNList = new FrozenNote[3];

                List<FrozenNote> frozenNotes = response.body();
                if (frozenNotes != null) {
                    // Liste in ein nach der Position geordneten Array umwandeln
                    for (FrozenNote fn : frozenNotes) {
                        fNList[fn.getPosition()] = fn;
                    }
                    Log.i("Fetching FNote List", String.format("FrozenNote list fNList %s fetched.",
                            new Gson().toJson(fNList)));

                    // Only update view after we have got all data we need
                    updateView();
                } else {
                    // sollte nie erreicht werden
                    Log.e("getfNList1", "There are no Frozen Note.");
                }
            }

            @Override
            public void onFailure(Call<List<FrozenNote>> call, Throwable t) {
                Log.e("getFNList", "Get FrozenNoteList failed.");
            }
        });

        // At this moment, the fNList is not yet loaded from server
        // return fNList;
    }

    /**
     * get Methode für die Liste der CoolNotes
     * hier verbinden mit dem Server
     */
    private void fetchCoolNoteList() {
        RetrofitClientInstance.getRetrofitInstance().create(CoolNoteService.class).getAllCoolNotes(flatshareId).enqueue(new Callback<List<CoolNote>>() {
            @Override
            public void onResponse(Call<List<CoolNote>> call, Response<List<CoolNote>> response) {
                cNList = new CoolNote[9];
                List<CoolNote> coolNotes = response.body();

                if (coolNotes != null) {
                    // Liste in ein nach der Position geordneten Array umwandeln
                    for (CoolNote cn : coolNotes) {
                        cNList[cn.getPosition()] = cn;
                    }
                    Log.i("Fetching CoolNote List", String.format("CoolNote list cNList %s fetched.",
                            new Gson().toJson(cNList)));

                    updateView();
                } else {
                    Log.e("getcNList", "There are no Cool Note.");
                }
            }

            @Override
            public void onFailure(Call<List<CoolNote>> call, Throwable t) {
                Log.e("getcNList", "Get CoolNoteList failed.");
            }
        });
    }

    /**
     * get Methode für die Liste der Mitglieder
     * hier verbinden mit dem Server
     *
     * @return
     */
    private void fetchMemberList() {
        RetrofitClientInstance.getRetrofitInstance().create(MembershipService.class).getMemberList(flatshareId).enqueue(new Callback<List<UserMembershipRepresentation>>() {
            @Override
            public void onResponse(Call<List<UserMembershipRepresentation>> call, Response<List<UserMembershipRepresentation>> response) {
                memberList = new UserMembershipRepresentation[15];
                List<UserMembershipRepresentation> members = response.body();
                if (members != null) {
                    for (int m = 0; m < members.size(); m++) {
                        memberList[m] = members.get(m);
                    }
                    Log.i("Fetching Member List", String.format("Member list %s fetched.", new Gson().toJson(members)));

                    updateView();
                } else {
                    Log.e("Fetching Memberlist", "There are no Members. members is null");
                }
            }

            @Override
            public void onFailure(Call<List<UserMembershipRepresentation>> call, Throwable t) {
                Log.e("Fetching Memberlist", "onFailure: There are no Members.");
            }
        });
    }

    /**
     * wird in getcNList() aufgerufen, updatet die MagnetListe und den Live Data dazu
     *
     * @param memberCommandlist die neu vom Server gegettete Liste wird hier übergeben
     */
    private void setLiveDataMagnetColorList(UserMembershipRepresentation[] memberCommandlist) {
        CoolNote[] cList = this.cNList;

        int i = 0;
        if (cNList != null) {
            for (CoolNote cn : cList) {
                if (cn != null) {
                    this.magnetColorList[i] = getMagnetColorByUserId(cn.getCreatorMembershipId(), memberCommandlist);
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

        Log.i("homeVM", String.format("Member Magnet Color list fetched: %s", new Gson().toJson(magnetColorList)));

        this.liveDataMagnetColorList.setValue(this.magnetColorList);
    }

    /**
     * wird in setLiveDataMagnetColorList benötigt
     *
     * @param id                Member ID
     * @param memberCommandlist
     * @return MagnetFarbe
     */
    private int getMagnetColorByUserId(String id, UserMembershipRepresentation[] memberCommandlist) {
        UserMembershipRepresentation[] mlist = memberCommandlist;

        if (mlist == null) {
            Log.e("member", "The member is not found");
            return MagnetColorUtilities.getDefaultMagnetColor(); // sollte nie erreicht werden... sonst heißt es, dass es den Member nicht gibt
        }

        for (UserMembershipRepresentation m : mlist) {
            if (m != null && m.getMemberId().equals(id)) {
                return MagnetColorUtilities.convertMagnetColor(m.getMagnetColor());
            }
        }

        return MagnetColorUtilities.getDefaultMagnetColor();
    }


    /**
     * wird in getcNList() aufgerufen, updatet die VisibilityListe und den Live Data dazu
     *
     * @param list die CoolNote Liste, die neu geupdatet wurde
     */
    private void setLiveDataVisibilityList(CoolNote[] list) {
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
     * Methode führt Activitywechsel zu CreateTextCoolNoteActivity durch,
     * wenn bereits neun CoolNotes existieren, erscheint eine Meldung
     *
     * @param view
     */
    public void onPlusButtonClicked(View view) {
        fetchData();
        LinkedList<Integer> emptyPositions = this.getListOfEmptySpaceForCoolNote();
        if (emptyPositions == null) {
            Toast.makeText(view.getContext(), "You can't add more Cool Note. Please delete some CoolNotes.", Toast.LENGTH_SHORT).show();

        } else {
            int position = this.getRandomEmptyPosition(emptyPositions);
            Context context = view.getContext();
            Intent intent = new Intent(context, CreateTextCoolNoteActivity.class);
            intent.putExtra("position", position);
            context.startActivity(intent);
        }

    }

    /**
     * Methode öffnet den MenüDrawer,
     *
     * @param view
     *//*
    public void onMenuButtonClicked(View view) {
        this.updateLists();
        Context context = view.getContext();
        Intent intent = new Intent(context, MenuDrawerActivity.class);
        context.startActivity(intent);
    }*/
    public void onRefreshButtonClicked(View view) {
        fetchData();
    }


    /**
     * Methode führt Activitywechsel zu FullTextCoolNoteActvity durch,
     * wenn auf die CoolNotes gedrückt wird, gleichzeitig wird geupdatet,
     * wenn die Note bereits gelöscht wurde, wird eine Meldung angezeigt
     *
     * @param view
     */
    public void openFullCoolNote(View view) {
        fetchData();
        int position = Integer.parseInt(view.getTag().toString());
        if (liveDataCNList.getValue() != null) {
            if (this.liveDataCNList.getValue()[position - 1] != null) {
                Context context = view.getContext();
                String cNid = this.liveDataCNList.getValue()[position - 1].getId();
                Intent intent = new Intent(context, FullTextCoolNoteActivity.class);
                intent.putExtra("coolNoteId", cNid);
                context.startActivity(intent);
            } else {
                Toast.makeText(view.getContext(), "This Cool Note was deleted.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(view.getContext(), "This Cool Note was deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Methode führt Activitywechsel zu FullTextFrozenNoteActvity durch,
     * wenn auf die FrozenNotes gedrückt wird
     *
     * @param view
     */
    public void openFullFrozenNote(View view) {
        fetchData();
        Context context = view.getContext();
        int position = Integer.parseInt(view.getTag().toString());

        if (liveDataCNList.getValue() != null) {
            if (this.liveDataFNList.getValue()[position - 1] != null) {
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
     * wird in onPlusButtonClicked() benutzt
     * Diese Methode sucht alle leeren Indizes in der Liste von CoolNotes
     *
     * @return IntArray von allen Indizes, die leer sind
     */
    private LinkedList<Integer> getListOfEmptySpaceForCoolNote() {
        CoolNote[] cnList = this.cNList;

        if (cnList == null) {
            LinkedList<Integer> nullarr = new LinkedList<>();
            for (int num = 0; num < 9; num++) {
                nullarr.add(num);
            }
            return nullarr;
        }

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
     * wird in onPlusButtonClicked() benutzt
     * berechnet eine zufällige leere Position
     *
     * @param list Liste mit den leeren Positionen auf der Pinnwand
     * @return Random Position
     */
    private int getRandomEmptyPosition(LinkedList<Integer> list) {
        LinkedList<Integer> emptyPositions = list;
        Random generator = new Random();
        int randomIndex = 0;
        while (randomIndex == 0) {
            randomIndex = generator.nextInt(emptyPositions.size());
            if (randomIndex == 0) {
                break;
            }
        }
        return emptyPositions.get(randomIndex);
    }


    // getter and setter

    public CoolNote[] getcNList() {
        return cNList;
    }

    public FrozenNote[] getfNList() {
        return fNList;
    }

    public Boolean[] getVisibilityList() {
        return visibilityList;
    }

    public Integer[] getMagnetColorList() {
        return magnetColorList;
    }

    public UserMembershipRepresentation[] getMemberList() {
        return memberList;
    }

    public void setcNList(CoolNote[] cnList){
        this.cNList = cnList;
    }
}
