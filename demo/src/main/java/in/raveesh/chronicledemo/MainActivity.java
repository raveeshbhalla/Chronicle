package in.raveesh.chronicledemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import in.raveesh.chronicle.Chronicle;
import in.raveesh.chronicle.EventOccurredListener;
import in.raveesh.chroniclesqlite.SqliteChronicle;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

	private static final String EVENT = "FAB_CLICKED";
	private Chronicle chronicle;
	private TextView lastOccurred;
	private TextView timesOccurred;
	private final long startTime = System.currentTimeMillis();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		chronicle = new SqliteChronicle(this);
		lastOccurred = findViewById(R.id.lastOccurred);
		timesOccurred = findViewById(R.id.timesOccurred);

		chronicle.addEventOccurredListener(EVENT, eventOccurredListener);

		setLastOccurred(chronicle.timeSinceLastOccurence(EVENT));
		setTimesOccurred(chronicle.timesDone(EVENT));

		findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				chronicle.did(EVENT);
			}
		});
	}

	private EventOccurredListener eventOccurredListener = new EventOccurredListener() {
		@Override
		public void eventOccurred(long timestamp) {
			setLastOccurred(timestamp);
			setTimesOccurred(chronicle.timesDone(EVENT));
		}
	};

	private void setLastOccurred(long timestamp) {
		lastOccurred.setText(String.format(Locale.getDefault(),
				getString(R.string.last_occurred),
				timestamp - startTime));
	}

	private void setTimesOccurred(int timesOccurred) {
		this.timesOccurred.setText(String.format(Locale.getDefault(),
				getString(R.string.times_occurred),
				timesOccurred));
	}

}
