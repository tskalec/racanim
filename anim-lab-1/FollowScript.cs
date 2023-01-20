using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FollowScript : MonoBehaviour
{
    [SerializeField]

    private Transform[] routes;
    private int routeToGo;
    private float t;
    private Vector3 objectPosition;
    private float speedModifier;
    private bool coroutineAllowed;

    // Start is called before the first frame update

    void Start()

    {

        routeToGo = 0;
        t = 0f;
        speedModifier = 0.5f;
        coroutineAllowed = true;

    }

    // Update is called once per frame

    void Update()

    {
        if (coroutineAllowed)

        {
            StartCoroutine(GoByTheRoute(routeToGo));
        }

    }

    private IEnumerator GoByTheRoute(int routeNum)

    {

        coroutineAllowed = false;

        Vector3 p0 = routes[routeNum].GetChild(0).position;

        Vector3 p1 = routes[routeNum].GetChild(1).position;

        Vector3 p2 = routes[routeNum].GetChild(2).position;

        Vector3 p3 = routes[routeNum].GetChild(3).position;

        while (t < 1)

        {

            t += Time.deltaTime * speedModifier;

            objectPosition = ((Mathf.Pow(-1*t, 3) + Mathf.Pow(3*t, 2) - 3*t + 1) / 6) * p0
				+ ((Mathf.Pow(3*t, 3) - Mathf.Pow(6*t, 2) + 4) / 6) * p1
				+ ((Mathf.Pow(-3*t, 3) + Mathf.Pow(3*t, 2) + 3*t + 1) / 6) * p2 
				+  (Mathf.Pow(t, 3) / 6) * p3;

			
            transform.position = objectPosition;

            yield return new WaitForEndOfFrame();

        }



        t = 0;

        speedModifier = speedModifier * 0.90f;

        routeToGo += 1;

        if (routeToGo > routes.Length - 1)

        {
            routeToGo = 0;

        }

        coroutineAllowed = true;

    }
}
