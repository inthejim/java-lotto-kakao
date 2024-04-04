import static org.assertj.core.api.Assertions.assertThat;

import domain.LottoStore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("LottoStore 단위 테스트")
public class LottoStoreTest {

    @Test
    void 로또_발권() {
        LottoStore lottoStore = new LottoStore(14_000);

        assertThat(lottoStore.getLottoCount()).isEqualTo(14);
    }
}
