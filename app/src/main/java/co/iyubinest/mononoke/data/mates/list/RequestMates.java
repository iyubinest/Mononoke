package co.iyubinest.mononoke.data.mates.list;

import co.iyubinest.mononoke.data.mates.Mate;
import io.reactivex.Flowable;
import java.util.List;

public interface RequestMates {
  Flowable<List<Mate>> connect();

  Flowable<RetrofitRequestMates.UpdateEvent> subscribeUpdates();

  class UpdateEvent {
  }

  class NewStatusEvent extends UpdateEvent {
    String user;
    String state;

    public NewStatusEvent(String user, String state) {
      this.user = user;
      this.state = state;
    }

    public String user() {
      return user;
    }

    public String state() {
      return state;
    }
  }

  class NewUserEvent extends UpdateEvent {
    Mate user;

    public Mate user() {
      return user;
    }
  }
}
