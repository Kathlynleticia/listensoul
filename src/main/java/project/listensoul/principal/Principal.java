package project.listensoul.principal;

import project.listensoul.model.Artista;
import project.listensoul.model.Musica;
import project.listensoul.model.TipoArtista;
import project.listensoul.repository.ArtistaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final ArtistaRepository repositorio;
    private Scanner leitura = new Scanner(System.in);

    public Principal(ArtistaRepository respositorio) {
        this.repositorio = respositorio;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    \n
                    1 - Cadastrar artistas
                    2 - Cadastrar músicas
                    3 - Listar músicas
                    4 - Buscar músicas por artístas
                    
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    listarMusicasBuscadas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void cadastrarArtista() {
        var novoArtista = "S";

        while (novoArtista.equalsIgnoreCase("s")) {
            System.out.println("Informe o nome do Artista: ");
            var nomeArtista = leitura.nextLine();
            System.out.println("Qual o tipo desse artista: (Solo, dupla ou banda)");
            var tipo = leitura.nextLine();
            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artista artista = new Artista(nomeArtista, tipoArtista);
            repositorio.save(artista);
            System.out.println("Cadastrar novo artista? (S/N)");
            novoArtista = leitura.nextLine();
        }
    }

    private void cadastrarMusica() {
        System.out.println("De qual artista é a música que deseja cadastrar? ");
        var nome = leitura.nextLine();
        Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nome);
        if (artista.isPresent()) {
            System.out.println("Informe o título da música: ");
            var nomeMusica = leitura.nextLine();
            Musica musica = new Musica(nomeMusica);
            artista.get().getMusicas().add(musica);
            musica.setArtista(artista.get());
            repositorio.save(artista.get());
        } else {
            System.out.println("Artista não encontrado.");
        }
    }
    private void listarMusicasBuscadas() {
        List<Artista> artistas = repositorio.findAll();
        artistas.forEach(a -> a.getMusicas().forEach(System.out::println));
    }

    private void buscarMusicasPorArtista() {
        System.out.println("Deseja buscar músicas de que artista: ");
        var nome = leitura.nextLine();
        List<Musica> musicas = repositorio.buscaMusicasPorArtista(nome);
        musicas.forEach(System.out::println);
    }


}

