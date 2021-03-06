package co.iyubinest.mononoke.data.team.get;
import co.iyubinest.mononoke.cache.Cache;
import co.iyubinest.mononoke.cache.MemoryCache;
import co.iyubinest.mononoke.common.RxUtils;
import io.reactivex.Flowable;
import java.util.List;
import retrofit2.Retrofit;

public class HttpTeamRepository implements TeamRepository {

  private final TeamService service;
  private final Cache<List<TeamService.TeamResponse>> cache;

  public HttpTeamRepository(final Retrofit retrofit) {
    service = retrofit.create(TeamService.class);
    cache = new MemoryCache<>();
  }

  @Override public Flowable<List<TeamService.TeamResponse>> get() {
    return Flowable.concat(cache.get(), service.team().retryWhen(RxUtils.incremental()))
        .take(1)
        .doOnNext(cache::save);
  }
}
