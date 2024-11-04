package lotto.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.util.Constants;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public boolean contain(int number) {
        return numbers.contains(number);
    }

    public int countSameNumbers(Lotto lotto) {
        return (int) numbers.stream()
                .filter(lotto::contain)
                .count();
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicates(numbers);
        validateRange(numbers);
    }

    private void validateRange(List<Integer> numbers) {
        numbers.stream()
                .filter(number -> number < Constants.MIN_LOTTO_NUMBER || number > Constants.MAX_LOTTO_NUMBER)
                .findAny()
                .ifPresent(number -> {
                    throw new IllegalArgumentException();
                });
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != Constants.LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicates(List<Integer> numbers) {
        if (Set.copyOf(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return numbers.stream()
                .sorted()
                .toList()
                .toString();
    }
}
