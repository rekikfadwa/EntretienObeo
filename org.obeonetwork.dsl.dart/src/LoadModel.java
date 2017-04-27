
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import dart.Asset;
import dart.DartFactory;
import dart.DartPackage;
import dart.Folder;
import dart.Package;
import dart.Project;

public class LoadModel {

	public static void main(String[] args) {

		// Initialize the model
		DartPackage.eINSTANCE.eClass();

		// Register the XMI resource factory for the .dartspec extension
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("dartspec", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();

		// Get the resource
		Resource resource = resSet.getResource(URI.createURI("model/dartlang.dartspec"), true);
		// Get the first model element and cast it to the right type
		Project myProject = (Project) resource.getContents().get(0);
		
		// Count the Number of Elements in the model
		TreeIterator<EObject> treeIterator = myProject.eAllContents();
		System.out.println("Number of Elements in the model : " + countItemsUsingIterator(treeIterator));
		
		// Print the label of each Element in the model
		System.out.println("Labels of all model elements: ");
		treeIterator = myProject.eAllContents();
		printLabelofElementsUsingIterator(treeIterator);

		// Add a new element on the model
		// Retrieve the default factory singleton
		DartFactory factory = DartFactory.eINSTANCE;
		// create the content of the model via this program
		Package myPackage = factory.createPackage();
		myPackage.setName("My Dart Library");
		Folder myFolder = factory.createFolder();
		myFolder.setName("My Folder");
		EList<Asset> assetList = myPackage.getAssets();
		assetList.add(myFolder);
		resource.getContents().add(myPackage);

		// Save the content.
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static int countItemsUsingIterator(TreeIterator<EObject> iterator) {
		int nItems = 0;
		while (iterator.hasNext()) {
			nItems++;
			iterator.next();
		}
		return nItems;
	}

	static void printLabelofElementsUsingIterator(TreeIterator<EObject> iterator) {
		EObject next;
		int nItems = 0;
		EClass eclass;
		EList<EAttribute> atrList;
		Iterator<EAttribute> it;
		while (iterator.hasNext()) {
			next = iterator.next();
			atrList= next.eClass().getEAllAttributes();
			it = atrList.iterator();
			while(it.hasNext()){			
				EAttribute at =it.next();
				if (at!=null && at.getName().equals("name")) {
					System.out.println(nItems + " - " + next.eGet(at));
				}
			}
//			if (next instanceof Asset) {
//				System.out.println(nItems + " - " + ((Asset) next).getName());
//			}else if (next instanceof Package) {
//				System.out.println(nItems + " - " + ((Package) next).getName());
//			}
			nItems++;
		}
	}

}
