using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Route : MonoBehaviour
{
    [SerializeField]

    private Transform[] controlPoints;

    private Vector3 gizmosPosition;
	
    private void OnDrawGizmos()

    {

        for(float t = 0; t <= 1; t += 0.05f)

        {
			
			// B-spline
            gizmosPosition = 
				  ((Mathf.Pow(-1*t, 3) + Mathf.Pow(3*t, 2) - 3*t + 1) / 6) * controlPoints[0].position 
				+ ((Mathf.Pow(3*t, 3) - Mathf.Pow(6*t, 2) + 4) / 6) * controlPoints[1].position 
				+ ((Mathf.Pow(-3*t, 3) + Mathf.Pow(3*t, 2) + 3*t + 1) /6) * controlPoints[2].position 
				+  (Mathf.Pow(t, 3) / 6) * controlPoints[3].position;

            Gizmos.DrawSphere(gizmosPosition, 0.25f);

        }
		
		// kontrolni poligon
		Gizmos.DrawLine
		(new Vector3(controlPoints[0].position.x, controlPoints[0].position.y, controlPoints[0].position.z), 
		 new Vector3(controlPoints[1].position.x, controlPoints[1].position.y, controlPoints[1].position.z));
		
		Gizmos.DrawLine
		(new Vector3(controlPoints[1].position.x, controlPoints[1].position.y, controlPoints[1].position.z), 
		 new Vector3(controlPoints[2].position.x, controlPoints[2].position.y, controlPoints[2].position.z));
		
		Gizmos.DrawLine
		(new Vector3(controlPoints[2].position.x, controlPoints[2].position.y, controlPoints[2].position.z), 
		 new Vector3(controlPoints[3].position.x, controlPoints[3].position.y, controlPoints[3].position.z));
		 
		
    }
}
