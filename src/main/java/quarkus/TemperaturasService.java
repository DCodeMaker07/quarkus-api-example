package quarkus;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TemperaturasService implements ITemperaturaService {

    private List<Temperatura> valores = new ArrayList<>();

    @Override
    public void addTemperatura(Temperatura t) {
        valores.add(t);
    }

    @Override
    public List<Temperatura> getAllTemperaturas() {
        return Collections.unmodifiableList(valores);
    }

    @Override
    public Optional<Temperatura> sacarTemperatura(String ciudad) {
        return this.valores.stream()
                .filter(t -> t.getCiudad().equals(ciudad))
                .findFirst();
    }

    @Override
    public boolean isEmpty() {
        return this.valores.isEmpty();
    }

    @Override
    public int maxima() {
        return this.valores.stream().mapToInt(Temperatura::getMaxima).max().orElse(0);
    }

}
