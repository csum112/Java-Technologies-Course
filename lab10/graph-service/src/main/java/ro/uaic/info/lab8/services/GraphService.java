package ro.uaic.info.lab8.services;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jgrapht.Graph;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.TopologicalOrderIterator;
import ro.uaic.info.lab8.accessors.DocumentClient;
import ro.uaic.info.lab8.dto.DocumentDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@ApplicationScoped
public class GraphService {
    @Inject @RestClient
    DocumentClient documentClient;

    public boolean containsCycles() {
        final List<DocumentDTO> documents = documentClient.getAll();
        final CycleDetector<DocumentDTO, DefaultEdge> cycleDetector = new CycleDetector<>(buildGraph(documents));
        return cycleDetector.detectCycles();
    }


    public List<DocumentDTO> topologicalSort() {
        final List<DocumentDTO> documents = documentClient.getAll();
        final var iterator = new TopologicalOrderIterator<>(buildGraph(documents));
        final Iterable<DocumentDTO> iterable = () -> iterator;
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    private static Graph<DocumentDTO, DefaultEdge> buildGraph(List<DocumentDTO> documents) {
        final Map<Long, DocumentDTO> map = buildMap(documents);
        final Graph<DocumentDTO, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);
        for (DocumentDTO doc : documents) {
            graph.addVertex(doc);
            for (Long ref : doc.getReferences()) {
                graph.addEdge(doc, map.get(ref));
            }
        }
        return graph;
    }

    private static Map<Long, DocumentDTO> buildMap(List<DocumentDTO> documents) {
        final Map<Long, DocumentDTO> map = new HashMap<>();
        for (DocumentDTO document : documents) {
            map.put(document.getDocumentID(), document);
        }
        return map;
    }

}
