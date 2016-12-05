package fragments.autocomplete;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.telerik.android.sdk.R;
import com.telerik.widget.autocomplete.AutoCompleteAdapter;
import com.telerik.widget.autocomplete.CompletionMode;
import com.telerik.widget.autocomplete.DisplayMode;
import com.telerik.widget.autocomplete.LayoutMode;
import com.telerik.widget.autocomplete.RadAutoCompleteTextView;
import com.telerik.widget.autocomplete.SuggestMode;
import com.telerik.widget.autocomplete.TokenModel;

import java.util.ArrayList;

import activities.ExampleFragment;

public class AutoCompleteTokenLayoutsFragment extends JsonDataLoadFragment implements ExampleFragment {

    private String[] data = new String[]{"Australia", "Albania","Austria", "Argentina", "Maldives","Bulgaria","Belgium","Cyprus","Italy","Japan",
            "Denmark","Finland","France","Germany","Greece","Hungary","Ireland",
            "Latvia","Luxembourg","Macedonia","Moldova","Monaco","Netherlands","Norway",
            "Poland","Romania","Russia","Sweden","Slovenia","Slovakia","Turkey","Ukraine",
            "Vatican City", "Chad", "China", "Chile"};
    private RadAutoCompleteTextView autocomplete = null;

    public String title() {
        return "Token Layouts";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.autocomplete_token_layouts, container, false);
        rootView.setFocusableInTouchMode(true);

        autocomplete = (RadAutoCompleteTextView) rootView.findViewById(R.id.autocmp);
        autocomplete.setSuggestMode(SuggestMode.SUGGEST);
        // >> autocomplete-display-mode
        autocomplete.setDisplayMode(DisplayMode.TOKENS);
        // << autocomplete-display-mode

        // >> autocomplete-layout-mode
        autocomplete.setTokensLayoutMode(LayoutMode.HORIZONTAL);
        // << autocomplete-layout-mode

        final AutoCompleteAdapter adapter = new AutoCompleteAdapter(this.getContext(),this.getTokenModelObjects(), R.layout.suggestion_item_layout);
        // >> autocomplete-completion-mode
        adapter.setCompletionMode(CompletionMode.STARTS_WITH);
        // << autocomplete-completion-mode
        autocomplete.setAdapter(adapter);

        Display display = this.getActivity().getWindowManager().getDefaultDisplay();
        int height  =  display.getHeight();
        autocomplete.setSuggestionViewHeight(height/4);

        this.setButtonActions(rootView);

        return rootView;
    }

    private ArrayList<TokenModel> getTokenModelObjects() {
        ArrayList<TokenModel> feedData = new ArrayList<TokenModel>();
        for(int i = 0; i < this.data.length; i++){
            TokenModel token = new TokenModel(this.data[i], null);
            feedData.add(token);
        }

        return feedData;
    }

    private void setButtonActions(View rootView){
        Button horizontalBtn = (Button)rootView.findViewById(R.id.horizontal_layout_btn);
        Button wrapBtn = (Button)rootView.findViewById(R.id.wrap_layout_btn);

        horizontalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autocomplete.setTokensLayoutMode(LayoutMode.HORIZONTAL);
                autocomplete.resetAutocomplete();
            }
        });

        wrapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autocomplete.setTokensLayoutMode(LayoutMode.WRAP);
                autocomplete.resetAutocomplete();
            }
        });
    }
}
